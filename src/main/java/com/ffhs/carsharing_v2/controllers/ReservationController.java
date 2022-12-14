package com.ffhs.carsharing_v2.controllers;

import com.ffhs.carsharing_v2.dto.Car;
import com.ffhs.carsharing_v2.dto.Reservation;
import com.ffhs.carsharing_v2.helpers.ReservationHelper;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import static com.ffhs.carsharing_v2.utilities.SessionUtils.getSessionFalse;

@Named
@SessionScoped
public class ReservationController implements Serializable {

    private static final long serialVersionUID = 1L;

    Reservation reservationDTO = new Reservation();

    public Reservation getReservationDTO(){
        return reservationDTO;
    }

    public void setReservationDTO(Reservation reservation) {
        this.reservationDTO = reservation;
    }

    public List<Reservation> getReservation(){
        try {
            return ReservationHelper.loadReservation();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public List<Reservation> getReservationUser(){
        try {
            return ReservationHelper.loadReservationUser();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public List<Car> getCarsReservation(){

        java.util.Date startDate = reservationDTO.getStart_date();

        java.util.Date endDate = reservationDTO.getEnd_date();

        try {
            return ReservationHelper.loadCarsReservation(startDate, endDate);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public String deleteReservation(int reservationId) {

        boolean valid = ReservationHelper.deleteReservation(reservationId);

        if(valid)
        {
            return "reservations?faces-redirect=true";
        }
        else {
            return "error";
        }
    }

    public String deleteReservationUser(int reservationId) {

        boolean valid = ReservationHelper.deleteReservation(reservationId);

        if(valid)
        {
            return "home.xhtml?faces-redirect=true";
        }
        else {
            return "error";
        }
    }

    public String addReservationForward(){
        return "addReservation.xhtml?faces-redirect=true";
    }

   public String editReservation(int reservationId){

        try {
            ReservationHelper.loadEditReservation(reservationId);
            return "editReservation?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public String updateReservation(int reservationId, String username, java.util.Date start_date, java.util.Date end_date) throws ParseException {

        boolean valid = ReservationHelper.updateReservation(reservationId, start_date, end_date);

        System.out.println(username);

        if(valid && getSessionFalse().getAttribute("username").equals("admin")){
            return "reservations.xhtml?faces-redirect=true";
        } else if (valid) {
            return "home.xhtml?faces-redirect=true";
        } else {
            return "error";
        }
    }

    public String forwardCarReservation() {
        return "availableCarsAdmin.xhtml?faces-redirect=true";
    }

    public String forwardCarReservationAdmin() {
        return "availableCarsAdmin.xhtml?faces-redirect=true";
    }

    public String reserveCar(String username, int carId, java.util.Date startDate, java.util.Date endDate){

        boolean valid = ReservationHelper.createReservation(username, carId, startDate, endDate);

        if(valid && getSessionFalse().getAttribute("username").equals("admin")){
            return "reservations.xhtml?faces-redirect=true";
        } else if (valid) {
            return "home.xhtml?faces-redirect=true";
        } else {
            return "error.xhtml?faces-redirect=true";
        }
    }
}
