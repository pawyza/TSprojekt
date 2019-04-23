/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.service;

import edu.ib.ts.connector.DbUtil;
import edu.ib.ts.model.Car;
import edu.ib.ts.model.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jakub Siembida
 */
public class AdminService extends ClientService {
//extends, bo showCars ma identyczną implementację

    private final String[] carColumn = {"`registrationNumber`", "`brand`", "`model`", "`perDayCost`"};

    
    @Override
    public List<Reservation> showReservations(int clientId) throws SQLException {
        return showReservations();//może mało eleganckie, ale dalej można odwoływać się przez interfejs, bez podawania poprawnego id
    }

    public List<Reservation> showReservations() throws SQLException {
        List<Reservation> reservations = null;
        final String sqlQuery = "SELECT r.idreservation,c.*,r.totalCost,r.pickUpDay,r.dropOffDay FROM reservation r left join car c on r.car=c.idcar";
        try (Connection connection = DbUtil.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);){
            reservations = new ArrayList<>();
            while (resultSet.next()) {
                int reservationid = resultSet.getInt("idreservation");
                int carid = resultSet.getInt("idcar");
                String registrationNumber = resultSet.getString("registrationNumber");
                String brand = resultSet.getString("brand");
                String model = resultSet.getString("model");
                double perDayCost = resultSet.getInt("perDayCost");
                Date pickUpDay = resultSet.getDate("pickUpDay");
                Date dropOffDay = resultSet.getDate("dropOffDay");
                double totalCost = resultSet.getInt("totalCost");
                reservations.add(new Reservation(reservationid,carid,registrationNumber,brand,model,perDayCost,pickUpDay,dropOffDay,totalCost));
            }
            return reservations;
        }
    }

    public void addCar(String[] data) throws SQLException {
        final String sqlQuery = "INSERT INTO `car`(`registrationNumber`, `brand`, `model`, `perDayCost`) VALUES(" + data[0] + ", " + data[1] + ", " + data[2] + ", " + data[3] + ")";
        System.out.println(sqlQuery);
        Connection connection = DbUtil.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
        connection.close();
    }
    
    public void updateCar(String id, Map<Integer, String> map) throws SQLException {
        String sqlQuery = "UPDATE `car` SET ";
        for(Integer key : map.keySet()){
            sqlQuery += carColumn[key] + " = " + map.get(key) + ",";
        }
        sqlQuery = sqlQuery.substring(0, sqlQuery.length()-1) + " WHERE `idcar` = " + id + ";";//usuwam ostatni przecinek
        System.out.println(sqlQuery);
        Connection connection = DbUtil.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
        connection.close();
    }

    public void removeCar(String id) throws SQLException {
        final String sqlQuery = "DELETE FROM `car` WHERE idcar = " + id + ";";
        Connection connection = DbUtil.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
        connection.close();
    }
    
    public void removeReservation(String id) throws SQLException {
        final String sqlQuery = "DELETE FROM `reservation` WHERE idreservation = " + id + ";";
        Connection connection = DbUtil.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
        connection.close();
    }
        
}
