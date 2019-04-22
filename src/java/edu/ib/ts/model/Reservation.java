/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.model;

import java.util.Date;


/**
 *
 * @author User
 */
public class Reservation {
    private int reservationId;
    private int carId;
    private String registrationNumber;
    private String brand;
    private String model;
    private double perDayCost;
    private Date pickUpDay;
    private Date dropOffDay;
    private double totalCost;

    public Reservation() {
    }

    public Reservation(int reservationId, int carId, String registrationNumber, String brand, String model, double perDayCost, Date pickUpDay, Date dropOffDay, double totalCost) {
        this.reservationId = reservationId;
        this.carId = carId;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.perDayCost = perDayCost;
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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPerDayCost() {
        return perDayCost;
    }

    public void setPerDayCost(double perDayCost) {
        this.perDayCost = perDayCost;
    }

    public Date getPickUpDay() {
        return pickUpDay;
    }

    public void setPickUpDay(Date pickUpDay) {
        this.pickUpDay = pickUpDay;
    }

    public Date getDropOffDay() {
        return dropOffDay;
    }

    public void setDropOffDay(Date dropOffDay) {
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
        return "Reservation{" + "reservationId=" + reservationId + ", carId=" + carId + ", registrationNumber=" + registrationNumber + ", brand=" + brand + ", model=" + model + ", perDayCost=" + perDayCost + ", pickUpDay=" + pickUpDay + ", dropOffDay=" + dropOffDay + ", totalCost=" + totalCost + '}';
    }
    
}