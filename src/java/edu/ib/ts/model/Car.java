/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.model;

/**
 *
 * @author User
 */
public class Car {
    
    private int carId;
    private String registrationNumber;
    private String brand;
    private String model;
    private double perDatCost;

    public Car() {
    }

    public Car(int carId, String registrationNumber, String brand, String model, double perDatCost) {
        this.carId = carId;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.perDatCost = perDatCost;
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

    public double getPerDatCost() {
        return perDatCost;
    }

    public void setPerDatCost(double perDatCost) {
        this.perDatCost = perDatCost;
    }

    @Override
    public String toString() {
        return "Car{" + "carId=" + carId + ", registrationNumber=" + registrationNumber + ", brand=" + brand + ", model=" + model + ", perDatCost=" + perDatCost + '}';
    }
    
}
