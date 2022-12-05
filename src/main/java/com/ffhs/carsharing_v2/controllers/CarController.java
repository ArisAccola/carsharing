package com.ffhs.carsharing_v2.controllers;

import com.ffhs.carsharing_v2.helpers.CarsHelper;
import com.ffhs.carsharing_v2.dto.Car;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;


/**
 * Function CarController
 * Bean for Front-End implementation
 */
@Named
@ViewScoped
public class CarController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final CarsHelper carsHelper;

    /**
     * Constructor
     */
    public CarController() {
        try {
            carsHelper = CarsHelper.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get list of Users from Database
     *
     * @return List<Cars> cars or null if error
     */
    public List<Car> getCars() {
        try {
            return carsHelper.loadCars();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    // TODO public void addCars() {}

    // TODO public void editCars() {}
}
