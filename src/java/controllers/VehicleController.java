/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * ??-??-2023      1.0                 DungMQ           First Implement
 * 03-06-2023      1.0                 DucTM            
 */
package controllers;

import dao.DAO;
import dao.VehicleDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Vehicle;

/**
 *
 * @author tranm
 */
public class VehicleController extends HttpServlet {

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
        try {
            DAO dao = new VehicleDAO();
            int agentID=3; //((User)session.getAttribute("user")).getID();
            List<Vehicle> list = dao.get(agentID);
            request.setAttribute("list", list);
        } catch (Exception ex) {
            Logger.getLogger(VehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("views/TravelAgent/VehiclesList.jsp").forward(request, response);
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
        String type=request.getParameter("type").trim();
        String driverName=request.getParameter("driverName").trim();
        String driverPhone=request.getParameter("driverPhone").trim();
        int maxPassenger = Integer.parseInt(request.getParameter("maxPassenger"));
        int agentID = 3;//((User)session.getAttribute("user")).getID();
        try {
            DAO dao = new VehicleDAO();
            dao.save(new Vehicle(type, driverName, driverPhone, maxPassenger, agentID));
        } catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
            request.setAttribute("vehicle", new Vehicle(type, driverName, driverPhone, maxPassenger, agentID));
        }
        doGet(request, response);
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

}
