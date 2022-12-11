package com.ffhs.carsharing_v2.helpers;

import com.ffhs.carsharing_v2.dto.Car;
import com.ffhs.carsharing_v2.dto.Reservation;
import com.ffhs.carsharing_v2.dto.User;
import com.ffhs.carsharing_v2.utilities.DataConnection;

import jakarta.faces.context.FacesContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ffhs.carsharing_v2.utilities.SessionUtils.getUsername;

public class ReservationHelper {

    public static List<Reservation> loadReservation() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement userStatement = null;

       try {
            connection = DataConnection.getConnection();
            assert connection != null;
            userStatement = connection.prepareStatement(
                    "select * from reservations inner join " +
                            "users u on reservations.username = u.username inner join " +
                            "cars c on reservations.carId = c.carId");
           return getReservationList(reservations, userStatement);
       }
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        finally {
            DataConnection.close(connection);
            DataConnection.close(userStatement);
        }
    }

    public static List<Car> loadCarsReservation(java.util.Date startDate, java.util.Date endDate) {
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement carsStatement = null;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            carsStatement = connection.prepareStatement("select * from cars where cars.carId not in(select r.carId from reservations as r where r.start_date >= ? and r.end_date <= ?)");

            carsStatement.setDate(1, new java.sql.Date(startDate.getTime()));
            carsStatement.setDate(2, new java.sql.Date(endDate.getTime()));

            ResultSet rs = carsStatement.executeQuery();

            System.out.println(carsStatement);

            while(rs.next()) {
                int carId = rs.getInt("carId");
                String carManufacturer = rs.getString("carManufacturer");
                String carModel = rs.getString("carModel");
                String carType = rs.getString("carType");
                String plateNumber = rs.getString("plateNumber");
                String status = rs.getString("status");

                //System.out.println(carManufacturer + " " + carModel + " " + carType + " " + plateNumber + " " + status);

                Car car = new Car(carId, carManufacturer, carModel, carType, plateNumber, status);
                cars.add(car);
            }
            return cars;
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
    public static List<Reservation> loadReservationUser() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement userStatement = null;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            userStatement = connection.prepareStatement(
                    "select * from reservations inner join " +
                            "users u on reservations.username = u.username inner join " +
                            "cars c on reservations.carId = c.carId where u.username=? && reservations.start_date >= CURDATE()");

            userStatement.setString(1, getUsername());

            System.out.println(userStatement);

            return getReservationList(reservations, userStatement);
        }
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        finally {
            DataConnection.close(connection);
            DataConnection.close(userStatement);
        }
    }

    private static List<Reservation> getReservationList(List<Reservation> reservations, PreparedStatement userStatement) throws SQLException {
        ResultSet rs = userStatement.executeQuery();

        while(rs.next()) {
            int reservationId= rs.getInt("reservationId");
            User user = new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"));
            Car car = new Car(rs.getInt("carId"), rs.getString("carManufacturer"), rs.getString("carModel"),
                    rs.getString("carType"), rs.getString("plateNumber"), rs.getString("status"));
            java.util.Date start_date = rs.getDate("start_date");
            java.util.Date end_date = rs.getDate("end_date");

            System.out.println(reservationId + " " + user.getUsername() + " " + car.getCarId()+ " " + start_date + " " + end_date);

            Reservation reservation = new Reservation(reservationId, user, car, start_date, end_date);

            reservations.add(reservation);
        }
        return reservations;
    }

    public static boolean deleteReservation(int reservationId){
        PreparedStatement deleteUserStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            deleteUserStatement= connection.prepareStatement("DELETE FROM reservations WHERE reservationId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            deleteUserStatement.setInt(1,reservationId);

            System.out.println(deleteUserStatement);

            rs =  deleteUserStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("Reservation successfully deleted");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(deleteUserStatement);
        }
        return false;
    }

    public static boolean createReservation(String username, int carId, java.util.Date start_date, java.util.Date end_date) {
        PreparedStatement createCarStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            createCarStatement= connection.prepareStatement("INSERT INTO reservations (username, carId, start_date, end_date) VALUES (?, ?,?, ?)");

            // Add parameters to the ?'s in the prepareStatement and execute
            createCarStatement.setString(1,username);
            createCarStatement.setInt(2, carId);
            createCarStatement.setDate(2, new java.sql.Date(start_date.getTime()));
            createCarStatement.setDate(2, new java.sql.Date(end_date.getTime()));

            rs =  createCarStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("Reservation successfully created");
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

    public static void loadEditReservation(int reservationId){

        Reservation editReservation = null;
        Connection connection = null;
        PreparedStatement editReservationStatement = null;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            editReservationStatement = connection.prepareStatement("select * from reservations inner join " +
                    "users u on reservations.username = u.username inner join " +
                    "cars c on reservations.carId = c.carId where reservationId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            editReservationStatement.setInt(1,reservationId);

            ResultSet rs = editReservationStatement.executeQuery();

            editReservation = new Reservation();

            rs.next();

            User user = new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"));
            Car car = new Car(rs.getInt("carId"), rs.getString("carManufacturer"), rs.getString("carModel"),
                    rs.getString("carType"), rs.getString("plateNumber"), rs.getString("status"));
            java.sql.Date start_date = rs.getDate("start_date");
            java.sql.Date end_date = rs.getDate("end_date");

            editReservation.setReservationId(reservationId);
            editReservation.setUser(user);
            editReservation.setCar(car);
            editReservation.setStart_date(new java.util.Date(start_date.getTime()));
            editReservation.setEnd_date(new java.util.Date(end_date.getTime()));

            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("editReservation", editReservation);
        }
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        finally {
            DataConnection.close(connection);
            DataConnection.close(editReservationStatement);
        }
    }

    public static boolean updateReservation(int reservationId, java.util.Date start_date, java.util.Date end_date){
        PreparedStatement updateUserStatement = null;
        Connection connection = null;
        int rs = 0;

        try{
            connection = DataConnection.getConnection();
            assert connection != null;
            updateUserStatement= connection.prepareStatement("UPDATE reservations SET start_date=?, end_date=? WHERE reservationId=?");

            // Add parameters to the ?'s in the prepareStatement and execute
            updateUserStatement.setDate(1, new java.sql.Date(start_date.getTime()));
            updateUserStatement.setDate(2, new java.sql.Date(end_date.getTime()));
            updateUserStatement.setInt(3, reservationId);

            System.out.println(updateUserStatement);

            rs =  updateUserStatement.executeUpdate();

            //User with Username and Password found
            if(rs == 1) {
                System.out.println("Reservation successfully updated");
                return true;
            }
        } catch (SQLException e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } finally {
            DataConnection.close(connection);
            DataConnection.close(updateUserStatement);
        }
        return false;
    }
}
