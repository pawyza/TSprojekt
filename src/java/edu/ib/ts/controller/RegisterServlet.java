/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.controller;

import edu.ib.ts.connector.DbUtil;
import edu.ib.ts.model.Client;
import edu.ib.ts.utilClass.InjectionStopper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
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
        request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String repassword = new InjectionStopper().prepareString(request.getParameter("repassword"));
        String forename = new InjectionStopper().prepareString(request.getParameter("forename"));
        String surname = new InjectionStopper().prepareString(request.getParameter("surname"));
        String pesel = new InjectionStopper().prepareString(request.getParameter("pesel"));
        String number = new InjectionStopper().prepareString(request.getParameter("number"));
        
        
        try {
            if (!login.equals("") && !password.equals("") && !forename.equals("") && !surname.equals("") && !pesel.equals("") && !number.equals("")
                    && password.equals(repassword) && !forename.matches("(.)*(\\d)") && !surname.matches("(.)*(\\d)") && !pesel.matches("(.)*(\\D)") && !number.matches("(.)*(\\D)")){
                if (checkPesel(pesel)){
                        try {
                            createClient(login, password, forename, surname, pesel, number);
                            request.getRequestDispatcher("registerSuccess.jsp").forward(request, response);
                        } catch (ClassNotFoundException | SQLException ex) {
                            ex.printStackTrace();
                            response.sendError(500);
                        }
                    } else {
                    
                        String message = "Pesel jest w użyciu!";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
            } else {
                        String message = "Podano zle wartosci!";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                response.sendError(500);
        }
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

     private void createClient(String login, String password, String forename, String surname, String pesel, String phoneNumber) throws SQLException, ClassNotFoundException {
        final String sqlQuery = "INSERT INTO client(login, password, forename, surname, pesel, phoneNumber) values ('" + login + "','" + password + "','" + forename + "','" + surname + "','" + pesel + "','" + phoneNumber + "')";
        Connection connection = DbUtil.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlQuery);
    }
     
      private boolean checkPesel(String pesel) throws SQLException, ClassNotFoundException {
        Client client = null;
        final String sqlQuery = "SELECT count(*) as count FROM client where pesel = '" + pesel + "'";
        try (Connection connection = DbUtil.getInstance().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);){
                    while (resultSet.next()) {
                            if(resultSet.getInt("count") > 0){
                                return false;
                            }
                            if(resultSet.getInt("count") == 0){
                                return true;
                            }
                    }
                    return false;
                }
    }
}
