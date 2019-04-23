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
import java.util.Map;
import java.util.TreeMap;
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
@WebServlet(urlPatterns = {"/carchange"})
public class CarChangeServlet extends HttpServlet {

    @Inject
    private AdminService adminService;
    
    private InjectionStopper is = new InjectionStopper();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        adminService = new AdminService();
        String mode = req.getParameter("mode");
        String sqlStatement = "";
        if("add".equals(mode)){
            String[] data = new String[4];
            data[0] = "'" + is.prepareString(req.getParameter("regnmbr")) + "'";
            data[1] = "'" + is.prepareString(req.getParameter("brand")) + "'";
            data[2] = "'" + is.prepareString(req.getParameter("model")) + "'";
            data[3] = "'" + is.prepareString(req.getParameter("cost")) + "'";
            try {
                adminService.addCar(data);
            } catch (SQLException ex) {
                Logger.getLogger(CarChangeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if("edit".equals(mode)) {
            String id = is.prepareString(req.getParameter("id"));
            String regnmbr = "'" + is.prepareString(req.getParameter("regnmbr")) + "'";
            String brand = "'" + is.prepareString(req.getParameter("brand")) + "'";
            String model = "'" + is.prepareString(req.getParameter("model")) + "'";
            String cost = "'" + is.prepareString(req.getParameter("cost")) + "'";
            Map<Integer, String> updateMap = new TreeMap<>();
            updateMap.put(0, regnmbr);
            updateMap.put(1, brand);
            updateMap.put(2, model);
            updateMap.put(3, cost);
            for(Integer key : updateMap.keySet()){//usuwam puste znaki
                if(updateMap.get(key).equals(""))
                    updateMap.remove(key);
            }
            try {
                adminService.updateCar(id, updateMap);
            } catch (SQLException ex) {
                Logger.getLogger(CarChangeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if("delete".equals(mode)){
            String id = is.prepareString(req.getParameter("id"));
            try {
                adminService.removeCar(id);
            } catch (SQLException ex) {
                Logger.getLogger(CarChangeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //tu jakiś exc wywalic mozna, jak ktos probuje na chybil trafil wpisać w pasku
        }
        resp.sendRedirect("adminView");
        
    }

    
    
}
