/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 01-07-2023      1.0                 DucTM           First Implement
 */
package controller;

import dao.BookingDAO;
import dao.impl.BookingDAOImpl;
import entity.Booking;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DucTM
 */
public class HandleBookingController extends HttpServlet {

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
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            BookingDAO dao = new BookingDAOImpl();
            String index = request.getParameter("index");
            request.getSession().setAttribute("index", index);
            if (request.getParameter("decline") != null) {
                String reason = request.getParameter("reason").trim();
                dao.update(new Booking(id, "Declined", reason));
                response.sendRedirect("bookinglist");
            }
            if (request.getParameter("cancel") != null) {
                String reason = request.getParameter("reason").trim();
                dao.update(new Booking(id, "Canceled", reason));
                request.getRequestDispatcher("history").forward(request, response);
            }
            if (request.getParameter("paid") != null) {
                dao.update(new Booking(id, "Paid", null));
                response.sendRedirect("bookinglist");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
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

}
