/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 29-06-2023      1.0                 DucTM           First Implement
 */
package controller;

import dao.BasicDAO;
import dao.BookingDAO;
import dao.StaffDAO;
import dao.impl.BookingDAOImpl;
import dao.impl.HotelDAOImpl;
//import dao.impl.RestaurantDAOlmpl;
import dao.impl.StaffDAOImpl;
import dao.impl.VehicleDAOImpl;
import entity.Booking;
import entity.Hotel;
//import entity.Restaurant;
import entity.Staff;
import entity.User;
import entity.Vehicle;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author tranm
 */
public class BookingListController extends HttpServlet {
    
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
            int agentId = ((User) request.getSession().getAttribute("user")).getId();
            BookingDAO dao = new BookingDAOImpl();
            int totalItems = dao.getTotalItems(agentId, "request");
            Object indexObj = request.getAttribute("index");
            int index;
            if (indexObj == null) {
                index = 1;
            } else {
                index = (int) indexObj;
            }
            BasicDAO basicDao = new VehicleDAOImpl();
            List<Vehicle> listVehicles = basicDao.getAll();
            basicDao=new HotelDAOImpl();
            List<Hotel> listHotels = basicDao.getAll();
//            basicDao=new RestaurantDAOlmpl();
//            List<Restaurant> listRestaurants = basicDao.getAll();
            StaffDAO sDao = new StaffDAOImpl();
            List<Staff> listStaff = sDao.getPageByAgent(agentId, new Pagination(sDao.getTotalItems(agentId), 100000, 1));
            Pagination page = new Pagination(totalItems, 10, index);
            List<Booking> list = dao.getBookingList(agentId, page);
            
            request.setAttribute("list", list);
            request.setAttribute("vehicles", listVehicles);
            request.setAttribute("hotels", listHotels);
//            request.setAttribute("restaurants", listRestaurants);
            request.setAttribute("staff", listStaff);
            request.setAttribute("page", page);
            request.getRequestDispatcher("views/TravelAgent/BookingList.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
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
        int index = Integer.parseInt(request.getParameter("index"));
        if (request.getParameter("first") != null) //click first
        {
            index = 1;
        }
        if (request.getParameter("last") != null) //click last
        {
            index = Integer.parseInt(request.getParameter("last"));
        }
        if (request.getParameter("Prev") != null) //click prev
        {
            index--;
        }
        if (request.getParameter("Next") != null) //click next
        {
            index++;
        }
        if (request.getParameter("btnIdx") != null) // click button number
        {
            index = Integer.parseInt(request.getParameter("btnIdx"));
        }
        request.setAttribute("index", index);
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
