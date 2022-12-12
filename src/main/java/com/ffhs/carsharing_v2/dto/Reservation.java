package com.ffhs.carsharing_v2.dto;

import java.util.Date;

/**
 * Java Representation of the Reservation Table in the database
 *
 * @author Aris M. Accola and Andreas Schwyter
 */
public class Reservation {

    private int reservationId;

    private User user;

    private Car car;

    private Date start_date;

    private Date end_date;

    /**
     * Constructor of Object Reservation without parameters
     */
    public Reservation()
    {
        super();
    }

    /**
     * Constructor of object Reservation with parameters
     *
     * @param reservationId the unique reservation id
     * @param user representation of the class user
     * @param car representation of the class car
     * @param start the start date of the reservation
     * @param end the end date of the reservation
     */
    public Reservation(int reservationId, User user, Car car, Date start, Date end){
        this.reservationId = reservationId;
        this.user = user;
        this.car = car;
        this.start_date = start;
        this.end_date = end;
    }


    /**
     * Getter Function for the Reservation ID
     *
     * @return reservationId
     */
    public int getReservationId() {
        return reservationId;
    }

    /**
     * Setter Function for the Reservation ID
     *
     * @param reservationId the unique reservationId
     */
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Getter Function for the start date
     *
     * @return start date of reservation
     */
    public Date getStart_date() {
        return start_date;
    }

    /**
     * Setter Function for the start date
     *
     * @param start_date the reservation's start date
     */
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    /**
     * Getter Function for the end date
     *
     * @return end date of reservation
     */
    public Date getEnd_date() {
        return end_date;
    }

    /**
     * Setter Function for the end date
     *
     * @param end_date the reservation's end date
     */
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    /**
     * Getter Function for the user associated to the reservation
     *
     * @return the user associated to the reservation
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter Function for the user associated to the reservation
     *
     * @param user the user associated to the reservation
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Getter Function for the car associated to the reservation
     *
     * @return the car associated to the reservation
     */
    public Car getCar() {
        return car;
    }

    /**
     * Setter Function for the car associated to the reservation
     *
     * @param car the car associated to the reservation
     */
    public void setCar(Car car) {
        this.car = car;
    }
}
