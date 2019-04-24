/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ib.ts.controller;

import com.sun.media.sound.InvalidFormatException;
import edu.ib.ts.model.Car;
import edu.ib.ts.model.Client;
import edu.ib.ts.model.Reservation;
import edu.ib.ts.service.ClientService;
import edu.ib.ts.service.Service;
import edu.ib.ts.utilClass.InjectionStopper;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
    
    private Client client;
    private InjectionStopper is = new InjectionStopper();
    
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
        client = (Client) request.getSession().getAttribute("client");
        
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
        /*
        Pattern datetime = Pattern.compile("20\\d{2}-[0-1]\\d-[0-3]\\d [0-2]\\d:[0-5]//d:[0-5]//d");
        //powyższy regex zapewnia:
        //lata 2000-2099, miesiące 0-19, dni 0-39, godziny 0-29, minuty 0-59, sekundy 0-59
        //do poprawy: miesiące, dni, godziny
        
        Pattern[] invalidDatetimePatterns = {
            Pattern.compile("-1[3-9]-"),//miesiące 13-19
            Pattern.compile("-00-"),//miesiąc 00
            Pattern.compile("-00 "),//dzień 00
            Pattern.compile("-02-3[0-1]"),//30 i 31 luty
            Pattern.compile("-04-31"),//31 kwie
            Pattern.compile("-06-31"),//31 cze
            Pattern.compile("-09-31"),//31 wrze
            Pattern.compile("-11-31"),//31 list
            Pattern.compile(" 2[4-9]"),//godziny od 24 wzwyż
        };
        */
        
        
        String carId = is.prepareString(request.getParameter("id"));
        String clientId = String.valueOf(client.getClientid());
        String pickupday = "'" + is.prepareString(request.getParameter("pickupday")) + "'";
        String dropoffday = "'" + is.prepareString(request.getParameter("dropoffday")) + "'";
        try{
            System.out.println(validateDatetimes(pickupday, dropoffday));
            if(validateDatetimes(pickupday, dropoffday)){
                String[] data = {carId, clientId, pickupday, dropoffday};
                addReservation(data);
            }
        } catch (DateTimeParseException e) {
            //tutaj obsłużyć złe formaty ldt
            System.out.println("zła data");
        } catch (SQLException ex) {
            Logger.getLogger(ClientViewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getSession().setAttribute("client", client);
        request.getRequestDispatcher("clientView.jsp").forward(request, response);
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
    
    private void addReservation(String[] data) throws SQLException{
        service = new ClientService();
        ((ClientService) service).addReservation(data);
    }
    
    private boolean validateDatetimes(String datetimeStr1, String datetimeStr2) throws DateTimeParseException{
        System.out.println(datetimeStr1.replace(" ", "T").replace("'", ""));
        System.out.println(datetimeStr2.replace(" ", "T").replace("'", ""));
        LocalDateTime datetime1 = LocalDateTime.parse(datetimeStr1.replace(" ", "T").replace("'", ""));
        LocalDateTime datetime2 = LocalDateTime.parse(datetimeStr2.replace(" ", "T").replace("'", ""));
        LocalDateTime now = LocalDateTime.now();
        //now < datetime1, now < datetime2, datetime1 < datetime2
        return (now.compareTo(datetime1) < 0) && (now.compareTo(datetime2) < 0) && (datetime1.compareTo(datetime2) < 0);
    }
}
