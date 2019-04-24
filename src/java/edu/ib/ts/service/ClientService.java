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
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jakub Siembida
 */
public class ClientService implements Service{

    @Override
    public List<Car> showCars() throws SQLException{
        List<Car> cars = null;
        final String sqlQuery = "SELECT car.*,r.dropOffDay FROM car left join (SELECT car,max(dropOffDay) as dropOffDay from reservation group by car) r on car.idcar=r.car";
        try (Connection connection = DbUtil.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);){
                cars = new ArrayList<>();
                    while (resultSet.next()) {
                            int carid = resultSet.getInt("idcar");
                            String registrationNumber = resultSet.getString("registrationNumber");
                            String brand = resultSet.getString("brand");
                            String model = resultSet.getString("model");
                            double perDayCost = resultSet.getInt("perDayCost");
                            Date dropOffDay = resultSet.getDate("dropOffDay");
                            cars.add(new Car(carid,registrationNumber,brand,model,perDayCost,dropOffDay));
                    }
                    return cars;
                }
 
    }

    @Override
    public List<Reservation> showReservations(int clientId) throws SQLException{
        List<Reservation> reservations = null;
        final String sqlQuery = "SELECT r.idreservation,c.*,r.totalCost,r.pickUpDay,r.dropOffDay FROM reservation r left join car c on r.car=c.idcar where  r.client = " + clientId;
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
    
    public void addReservation(String[] data) throws SQLException{
        //dane: {carId, clientId, pickupday, dropoffday};
        double perDayCost = getPerDayCarCost(data[0]);
        LocalDate ld1 = LocalDate.parse(data[2].split(" ")[0].replace("'", ""));//tylko część z datami, cena wg dnia
        LocalDate ld2 = LocalDate.parse(data[3].split(" ")[0].replace("'", ""));
        int daysBetween = (int)DAYS.between(ld1, ld2);//rzutuje z longa na inta, dane nie są aż tak duże
        final String sqlQuery = "INSERT INTO reservation(car, client, pickUpDay, dropOffDay, totalCost) VALUES(" + data[0] + ", " + data[1] + ", " + data[2] +", " + data[3] + ", " + daysBetween * perDayCost + ");";
        
        Connection connection = DbUtil.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
        connection.close();
    }

    private double getPerDayCarCost(String string) throws SQLException{
        final String sqlQuery = "SELECT perDayCost FROM car WHERE idcar = " + string + ";";
        double output = 0.0;
        try (Connection connection = DbUtil.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);){
            resultSet.next();
            output = resultSet.getInt("perDayCost");//tylko 1 wynik
        }
        return output;
    }
    
}
