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
public class Client {
    
    private int clientid;
    private String forename;
    private String surname;
    private String pesel;
    private int phoneNumber;

    public Client() {
    }

    public Client(int clientid, String forename, String surname, String pesel, int phoneNumber) {
        this.clientid = clientid;
        this.forename = forename;
        this.surname = surname;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return "Client{" + "clientid=" + clientid + ", forename=" + forename + ", surname=" + surname + ", pesel=" + pesel + ", phoneNumber=" + phoneNumber + '}';
    }
}
