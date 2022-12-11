package com.ffhs.carsharing_v2.dto;

import java.util.Date;

/**
 * Java Representation of the Cars table in the database
 *
 * @author Aris M. Accola and Andreas Schwyter
 */
public class Reservation {

    private int reservationId;

    private User user;

    private Car car;

    private Date start_date;

    private Date end_date;

    public Reservation()
    {
        super();
    }

    public Reservation(int reservationId, User user, Car car, Date start, Date end){
        this.reservationId = reservationId;
        this.user = user;
        this.car = car;
        this.start_date = start;
        this.end_date = end;
    }


    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
