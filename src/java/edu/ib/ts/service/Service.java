/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.service;

import edu.ib.ts.model.Car;
import edu.ib.ts.model.Reservation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jakub Siembida
 */
public interface Service {
    public List<Car> showCars() throws SQLException;
    public List<Reservation> showReservations(int clientId) throws SQLException;
}
