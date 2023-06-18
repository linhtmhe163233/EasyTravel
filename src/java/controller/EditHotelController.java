/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BasicDAO;
import dao.impl.HotelDAOImpl;
import entity.Hotel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ngan Ha
 */
@WebServlet(name = "EditHotelController", urlPatterns = {"/edit"})
public class EditHotelController extends HttpServlet {

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
            out.println("<title>Servlet EditHotelController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditHotelController at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("HotelId"));
        try {
            HotelDAOImpl dao = new HotelDAOImpl();
            Hotel hotel = dao.getHotelById(id);
            request.setAttribute("hotel", hotel);
        } catch (Exception ex) {
            Logger.getLogger(EditHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("views/TravelAgent/EditHotel.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name").trim();
        int stars = Integer.parseInt(request.getParameter("stars"));
        int room_available = Integer.parseInt(request.getParameter("room_available"));
        String phone = request.getParameter("phone").trim();
        String location = request.getParameter("location").trim();
        HttpSession session = request.getSession();

        try {
            HotelDAOImpl dao = new HotelDAOImpl();
            dao.updateHotle(id, name, stars, room_available, phone, location);
            response.sendRedirect("./hotels");
        } catch (Exception e) {
            request.setAttribute("message", e.getMessage());
            request.setAttribute("hotel", new Hotel(name, stars, room_available, phone, location));

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
