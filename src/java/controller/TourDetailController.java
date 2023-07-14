/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 14-06-2023      1.0                 DucTM           First Implement
 */
package controller;

import dao.FeedbackDAO;
import dao.TourDAO;
import dao.impl.FeedbackDAOImpl;
import dao.impl.TourDAOImpl;
import entity.FeedbackThread;
import entity.Tour;
import entity.User;
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
public class TourDetailController extends HttpServlet {

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
            FeedbackDAO daoo = new FeedbackDAOImpl();
            TourDAO dao = new TourDAOImpl();
            String idStr = request.getParameter("id");
            if (idStr == null || !idStr.matches("^[0-9]+$")) {
                response.sendRedirect("home");
                return;
            }
            String book = request.getParameter("book");
            request.setAttribute("book", book);
            int id = Integer.parseInt(idStr);
            List<Tour> list = dao.get(id);
            HttpSession session = request.getSession();
            User acc = (User) session.getAttribute("user");
            int touristID = -1;
            if (acc != null) {
                touristID = acc.getId();
            }
            boolean checkFeedback = daoo.checkDone(touristID, id);
            request.setAttribute("checkFeedback", checkFeedback);
            request.setAttribute("tour", list.get(0));
            int totalItems = daoo.getTotalItems(id);
            int index = 1;
            String indexStr = request.getParameter("index");
            if (indexStr != null && indexStr.matches("^[0-9]+$")) {
                index = Integer.parseInt(indexStr);
                request.setAttribute("scroll", "scroll");
            }
            Pagination page = new Pagination(totalItems, 10, index);
            List<FeedbackThread> listfb = daoo.getPage(page, id);
            request.setAttribute("page", page);
            request.setAttribute("listfb", listfb);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("views/TourDetail.jsp").forward(request, response);
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
            TourDAO dao = new TourDAOImpl();
            int id = Integer.parseInt(request.getParameter("id"));
            if (request.getParameter("disable") != null) {
                dao.delete(new Tour(id));
            }
            if (request.getParameter("enable") != null) {
                dao.enable(id);
            }
            response.sendRedirect("home");
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
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
