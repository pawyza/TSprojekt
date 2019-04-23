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
import edu.ib.ts.service.ClientService;
import edu.ib.ts.service.Service;
import edu.ib.ts.utilClass.InjectionStopper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
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

    @Inject
    private Service service;
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
    }

    private List<Car> getCar() throws SQLException, ClassNotFoundException {
        service = new ClientService();
        return service.showCars();
    }
    
    private List<Reservation> getReservation(int clientId) throws SQLException, ClassNotFoundException {
        service = new ClientService();
        return service.showReservations(clientId);
    }
}
