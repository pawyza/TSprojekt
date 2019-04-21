/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.controller;

import edu.ib.ts.utilClass.InjectionStopper;
import edu.ib.ts.connector.DbUtil;
import edu.ib.ts.model.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
@WebServlet("/login")
public class loginServlet extends HttpServlet {

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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        
        String login = new InjectionStopper().prepareString(request.getParameter("login"));
        String password = new InjectionStopper().prepareString(request.getParameter("password"));
        
        if (!login.equals("") && !password.equals("")){
            try{
                Client client = getClient(login,password);
                if(client != null){
                    request.setAttribute("client", client);
                    request.getRequestDispatcher("clientView.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("loginError.jsp").forward(request, response);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                response.sendError(500);
            }
        } else
                request.getRequestDispatcher("loginError.jsp").forward(request, response);
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
    
    private Client getClient(String login, String password) throws SQLException, ClassNotFoundException {
        Client client = null;
        final String sqlQuery = "SELECT * FROM client where login = '" + login + "' and password = '" + password + "'";
        try (Connection connection = DbUtil.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);){
                    while (resultSet.next()) {
                            int clientid = resultSet.getInt("idclient");
                            String forename = resultSet.getString("forename");
                            String surname = resultSet.getString("surname");
                            String pesel = resultSet.getString("pesel");
                            int phoneNumber = resultSet.getInt("phoneNumber");
                            client = new Client(clientid,forename,surname,pesel,phoneNumber);
                    }
                    return client;
                }
    }
}
