/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.controller;

import edu.ib.ts.service.AdminService;
import edu.ib.ts.utilClass.InjectionStopper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jakub Siembida
 */
@WebServlet(urlPatterns = {"/reschange"})
public class ReservationChangeServlet extends HttpServlet {

    @Inject
    private AdminService adminService;
    
    private InjectionStopper is = new InjectionStopper();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = is.prepareString(req.getParameter("id"));
        adminService = new AdminService();
        try {
            adminService.removeReservation(id);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationChangeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
