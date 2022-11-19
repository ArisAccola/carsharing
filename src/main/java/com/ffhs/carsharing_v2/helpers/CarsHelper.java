package com.ffhs.carsharing_v2.helpers;

import com.ffhs.carsharing_v2.pojos.Cars;
import com.ffhs.carsharing_v2.utilities.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarsHelper {
    private static CarsHelper instance;

    public static CarsHelper getInstance() throws Exception {
        if (instance == null) {
            instance = new CarsHelper();
        }
            return instance;
    }

    public List<Cars> getCars() throws Exception {
        List<Cars> cars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement carsStatement;

        try {
            connection = DataConnection.getConnection();
            assert connection != null;
            carsStatement = connection.prepareStatement("select * from cars");
            ResultSet rs = carsStatement.executeQuery();

            while(rs.next()) {
                String carManufacturer = rs.getString("carManufacturer");
                String carModel = rs.getString("carModel");
                String carType = rs.getString("carType");
                String plateNumber = rs.getString("plateNumber");
                String status = rs.getString("status");

                Cars car = new Cars(carManufacturer, carModel,carType,plateNumber,status);
                cars.add(car);

                System.out.println(carManufacturer + " " + carModel + " " + carType + " " + plateNumber + " " + status);
            }
            return cars;
        }
        catch(Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        finally {
            DataConnection.close(connection);
        }
    }
}
