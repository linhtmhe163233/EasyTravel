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
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author tranm
 */
public class TourDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TourDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Tour id: " + request.getParameter("id") + "</h1>");
            out.println("<h1>Tour id: " + request.getParameter("id").isEmpty() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            Object indexObj = request.getAttribute("index");
            int index;
            if (indexObj == null) {
                index = 1;
            } else {
                index = (int) indexObj;
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
        if (request.getParameter("disable") == null && request.getParameter("enable") == null) {
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
            return;
        }
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
