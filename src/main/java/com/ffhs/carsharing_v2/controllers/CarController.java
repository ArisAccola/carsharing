package com.ffhs.carsharing_v2.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ffhs.carsharing_v2.helpers.CarsHelper;
import com.ffhs.carsharing_v2.pojos.Cars;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;


@Named
public class CarController implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Cars> cars;
    private CarsHelper carsHelper;

    public CarController() throws Exception {
        cars = new ArrayList<Cars>();
        carsHelper = CarsHelper.getInstance();
    }

    public List<Cars> getCars() {
        return cars;
    }

    public void loadCars () {
        cars.clear();
        try {
            cars = carsHelper.getCars();
        }catch (Exception e) {
            addErrorMessage (e);
        }
    }

    private void addErrorMessage(Exception ex) {
        FacesMessage message = new FacesMessage(ex.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
