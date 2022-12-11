package com.ffhs.carsharing_v2.helpers;

import com.ffhs.carsharing_v2.dto.Car;
import com.ffhs.carsharing_v2.utilities.DataConnection;
import jakarta.faces.context.FacesContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarsHelper {
    private static CarsHelper instance;

    public static CarsHelper getInstance() throws Exception {
        try{
            if (instance == null) {
                instance = new CarsHelper();
            }
            return instance;
        } catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static List<Car> loadCars() {
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement carsStatement = null;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            carsStatement = connection.prepareStatement("select * from cars");
            return getCars(cars, carsStatement);
        }
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        finally {
            DataConnection.close(connection);
            DataConnection.close(carsStatement);
        }
    }

    static List<Car> getCars(List<Car> cars, PreparedStatement carsStatement) throws SQLException {
        ResultSet rs = carsStatement.executeQuery();

        while(rs.next()) {
            int carId = rs.getInt("carId");
            String carManufacturer = rs.getString("carManufacturer");
            String carModel = rs.getString("carModel");
            String carType = rs.getString("carType");
            String plateNumber = rs.getString("plateNumber");
            String status = rs.getString("status");

            //System.out.println(carManufacturer + " " + carModel + " " + carType + " " + plateNumber + " " + status);

            Car car = new Car(carId, carManufacturer, carModel,carType,plateNumber,status);
            cars.add(car);
        }
        return cars;
    }

    public static boolean deleteCar(int carId){
        PreparedStatement deleteCarStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            deleteCarStatement= connection.prepareStatement("DELETE FROM cars WHERE carId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            deleteCarStatement.setInt(1,carId);

            System.out.println(deleteCarStatement);

            rs =  deleteCarStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("Car successfully deleted");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(deleteCarStatement);
        }
        return false;
    }

    public static boolean createCar(String carManufacturer, String carModel, String carType, String plateNumber, String status) {
        PreparedStatement createCarStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            createCarStatement= connection.prepareStatement("INSERT INTO cars (carManufacturer, carModel, carType, plateNumber, status) VALUES (?, ?, ?, ?, ?)");

            // Add parameters to the ?'s in the prepareStatement and execute
            createCarStatement.setString(1,carManufacturer);
            createCarStatement.setString(2, carModel);
            createCarStatement.setString(3, carType);
            createCarStatement.setString(4, plateNumber);
            createCarStatement.setString(5, status);

            System.out.println(createCarStatement);

            rs =  createCarStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("Car successfully created");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(createCarStatement);
        }
        return false;
    }

    public static void loadEditCar(int editCarId){

        Car editCar = null;
        Connection connection = null;
        PreparedStatement editCarStatement = null;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            editCarStatement = connection.prepareStatement("SELECT * FROM cars WHERE carId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            editCarStatement.setInt(1, editCarId);

            ResultSet rs = editCarStatement.executeQuery();

            editCar = new Car();

            rs.next();
            editCar.setCarId(rs.getInt("carId"));
            editCar.setCarManufacturer(rs.getString("carManufacturer"));
            editCar.setCarModel(rs.getString("carModel"));
            editCar.setCarType(rs.getString("carType"));
            editCar.setPlateNumber(rs.getString("plateNumber"));
            editCar.setStatus(rs.getString("status"));

            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("editCar", editCar);
        }
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        finally {
            DataConnection.close(connection);
            DataConnection.close(editCarStatement);
        }
    }

    public static boolean updateCar(int carId, String carManufacturer, String carModel, String carType, String plateNumber, String status){
        PreparedStatement updateCarStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            updateCarStatement= connection.prepareStatement("UPDATE cars SET carManufacturer=?, carModel=?, carType=?, plateNumber=?, status=? WHERE carId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            updateCarStatement.setString(1, carManufacturer);
            updateCarStatement.setString(2, carModel);
            updateCarStatement.setString(3,carType);
            updateCarStatement.setString(4,plateNumber);
            updateCarStatement.setString(5,status);
            updateCarStatement.setInt(6,carId);

            rs =  updateCarStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("Car successfully updated");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(updateCarStatement);
        }
        return false;
    }

}
