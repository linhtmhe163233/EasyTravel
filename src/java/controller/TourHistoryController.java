/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 27-06-2023      1.0                 DucTM           First Implement
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Pagination;

/**
 *
 * @author DucTM
 */
public class TourHistoryController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {
            BookingDAO dao = new BookingDAOImpl();
            dao.finishTours();
        } catch (Exception ex) {
            Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
            BookingDAO dao = new BookingDAOImpl();
            int touristId = ((User) request.getSession().getAttribute("user")).getId();
            int totalItems = dao.getTotalItems(touristId, "history");
            Object indexObj = request.getAttribute("index");
            int index;
            if (indexObj == null) {
                index = 1;
            } else {
                index = (int) indexObj;
            }
            Pagination page = new Pagination(totalItems, 10, index);
            List<Booking> list = dao.getTourHistory(touristId, page);
            request.setAttribute("list", list);
            request.setAttribute("page", page);
            request.getRequestDispatcher("views/Tourist/TourHistory.jsp").forward(request, response);
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
