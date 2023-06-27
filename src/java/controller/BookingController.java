/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 27-06-2023      1.0                 DucTM           First Implement
 */

package controller;

import dao.BasicDAO;
import dao.impl.BookingDAOImpl;
import entity.Booking;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

/**
 *
 * @author DucTM
 */
public class BookingController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookingController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingController at " + request.getContextPath () + "</h1>");
            out.println("<h1>" + request.getParameter("touristsQuantity") + "</h1>");
            out.println("<h1>" + request.getParameter("startDate") + "</h1>");
            out.println("<h1>" + request.getParameter("note") + "</h1>");
            out.println("<h1>" + request.getParameter("tourId") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int touristId = ((User)request.getSession().getAttribute("user")).getId();
        int tourId = Integer.parseInt(request.getParameter("tourId"));
        Timestamp bookTime = new Timestamp(System.currentTimeMillis());
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        int touristsQuantity = Integer.parseInt(request.getParameter("touristsQuantity"));
        String status = "Processing";
        String note = request.getParameter("note");
        
        Booking booking = new Booking(touristId, tourId, bookTime, startDate, touristsQuantity, status, note);
        try {
            BasicDAO dao = new BookingDAOImpl();
            dao.save(booking);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
        }
        response.sendRedirect("tour?id="+tourId);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
