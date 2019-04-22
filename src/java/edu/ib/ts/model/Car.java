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
public class Car {
    
    private int carId;
    private String registrationNumber;
    private String brand;
    private String model;
    private double perDayCost;
    private Date dropOffDay;

    public Car() {
    }

    public Car(int carId, String registrationNumber, String brand, String model, double perDayCost, Date dropOffDay) {
        this.carId = carId;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.perDayCost = perDayCost;
        this.dropOffDay = dropOffDay;
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

    public void setPerDayCost(double perDatCost) {
        this.perDayCost = perDatCost;
    }

    public Date getDropOffDay() {
        return dropOffDay;
    }

    public void setDropOffDay(Date dropOffDay) {
        this.dropOffDay = dropOffDay;
    }

    @Override
    public String toString() {
        return "Car{" + "carId=" + carId + ", registrationNumber=" + registrationNumber + ", brand=" + brand + ", model=" + model + ", perDayCost=" + perDayCost + ", availability=" + dropOffDay + '}';
    }
    
}
