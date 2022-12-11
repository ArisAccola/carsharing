package com.ffhs.carsharing_v2.controllers;

import com.ffhs.carsharing_v2.helpers.CarsHelper;
import com.ffhs.carsharing_v2.dto.Car;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;



/**
 * Function CarController
 * Bean for Front-End implementation
 */
@Named
@SessionScoped
public class CarController implements Serializable {

    private static final long serialVersionUID = 1L;

    Car carDTO = new Car();

    public Car getCarDTO(){
        return carDTO;
    }

    public void setReservationDTO(Car car) {
        this.carDTO = car;
    }


    /**
     * Constructor
     */
    public CarController() {
        super();
    }

    /**
     * Get list of Users from Database
     *
     * @return List<Cars> cars or null if error
     */
    public List<Car> getCars() {
        try {
            return CarsHelper.loadCars();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public String addCarForward(){
        return "addCar?faces-redirect=true";
    }

    public String addCar(String carManufacturer, String carModel, String carType, String plateNumber, String status){

        boolean valid = CarsHelper.createCar(carManufacturer, carModel, carType, plateNumber, status);

        if(valid)
        {
            return "cars";
        }
        else {
            return "error";
        }
    }

    public String editCar(int carId){

        try {
            CarsHelper.loadEditCar(carId);
            return "editCar?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public String updateCar(int carId, String carManufacturer, String carModel, String carType, String plateNumber, String status){

        boolean valid = CarsHelper.updateCar(carId, carManufacturer, carModel, carType, plateNumber, status);

        if(valid)
        {
            return "cars";
        }
        else {
            return "error";
        }
    }

    public String deleteCar(int carId){
        boolean valid = CarsHelper.deleteCar(carId);

        if(valid)
        {
            return "cars";
        }
        else {
            return "error";
        }
    }

}
