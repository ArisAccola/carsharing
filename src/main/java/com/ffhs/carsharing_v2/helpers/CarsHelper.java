package com.ffhs.carsharing_v2.helpers;

import com.ffhs.carsharing_v2.dto.Car;
import com.ffhs.carsharing_v2.utilities.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Car> loadCars() {
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement carsStatement = null;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            carsStatement = connection.prepareStatement("select * from cars");
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
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        finally {
            DataConnection.close(connection);
            DataConnection.close(carsStatement);
        }
    }
}
