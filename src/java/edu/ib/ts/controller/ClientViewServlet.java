/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.controller;

import edu.ib.ts.connector.DbUtil;
import edu.ib.ts.model.Car;
import edu.ib.ts.model.Client;
import edu.ib.ts.model.Reservation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet("/clientView")
public class ClientViewServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Client client = (Client) request.getSession().getAttribute("client");
        
        try{
                List<Car> cars = getCar();
                request.setAttribute("cars", cars);
                List<Reservation> reservations = getReservation(client.getClientid());
                request.setAttribute("reservations", reservations);
                request.getRequestDispatcher("clientView.jsp").forward(request, response);
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                response.sendError(500);
            }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Car> getCar() throws SQLException, ClassNotFoundException {
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
    
    private List<Reservation> getReservation(int clientId) throws SQLException, ClassNotFoundException {
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
}
