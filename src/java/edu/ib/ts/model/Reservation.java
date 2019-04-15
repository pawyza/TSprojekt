/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.model;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Reservation {
    private int reservationId;
    private int carId;
    private int clientId;
    private LocalDate pickUpDay;
    private LocalDate dropOffDay;
    private double totalCost;

    public Reservation() {
    }
    
    public Reservation(int reservationId, int carId, int clientId, LocalDate pickUpDay, LocalDate dropOffDay, double totalCost) {
        this.carId = carId;
        this.clientId = clientId;
        this.pickUpDay = pickUpDay;
        this.dropOffDay = dropOffDay;
        this.totalCost = totalCost;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
    
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getPickUpDay() {
        return pickUpDay;
    }

    public void setPickUpDay(LocalDate pickUpDay) {
        this.pickUpDay = pickUpDay;
    }

    public LocalDate getDropOffDay() {
        return dropOffDay;
    }

    public void setDropOffDay(LocalDate dropOffDay) {
        this.dropOffDay = dropOffDay;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Reservation{" + "reservationId=" + reservationId + ", carId=" + carId + ", clientId=" + clientId + ", pickUpDay=" + pickUpDay + ", dropOffDay=" + dropOffDay + ", totalCost=" + totalCost + '}';
    }
     
}
