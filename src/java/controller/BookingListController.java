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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author DucTM
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
            HttpSession session = request.getSession();
            int agentId = ((User) session.getAttribute("user")).getId();
            BookingDAO dao = new BookingDAOImpl();
            int totalItems = dao.getTotalItems(agentId, "request");
            String indexStr = request.getParameter("index");
            if (session.getAttribute("indexStr") != null) {
                indexStr = (String) session.getAttribute("index");
            }
            int index = 1;
            if (indexStr != null && indexStr.matches("^[0-9]+$")) {
                index = Integer.parseInt(indexStr);
            }
            BasicDAO vDAO = new VehicleDAOImpl();
            List<Vehicle> listVehicles = vDAO.getAll();
            vDAO = new HotelDAOImpl();
            List<Hotel> listHotels = vDAO.getAll();
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
