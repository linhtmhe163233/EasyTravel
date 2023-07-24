/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 27-06-2023      1.0                 DucTM           First Implement
 * 28-06-2023      1.0                 DucTM           Change redirect link
 */
package controller;

import dao.BookingDAO;
import dao.impl.BookingDAOImpl;
import entity.Booking;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author DucTM
 */
public class BookingController extends HttpServlet {

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
        response.sendRedirect("home");
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
        int touristId = ((User) request.getSession().getAttribute("user")).getId();
        int tourId = Integer.parseInt(request.getParameter("tourId"));
        int tripLength = Integer.parseInt(request.getParameter("tripLength"));
        Timestamp bookTime = new Timestamp(System.currentTimeMillis());
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        int touristsQuantity = Integer.parseInt(request.getParameter("touristsQuantity"));
        String payment = request.getParameter("payment");
        String status = "Unpaid";
        String note = request.getParameter("note").trim();
        Booking booking = new Booking(touristId, tourId, bookTime, startDate, touristsQuantity, status, note, payment);
        try {
            BookingDAO dao = new BookingDAOImpl();
            if (!dao.checkSchedule(touristId, startDate, new Date(startDate.getTime() + tripLength * 24 * 60 * 60 * 1000))) {
                request.getSession().setAttribute("toast", "New request overlaps with tour's schedule!");
                response.sendRedirect("tour?id=" + tourId);
                return;
            }
            dao.save(booking);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
        }
        response.sendRedirect("history");
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
