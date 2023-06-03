/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 29-05-2023      1.0                 DucTM           First Implement
 * 30-05-2023      1.0                 DucTM           Add save image
 */
package controllers;

import dao.DAO;
import dao.TourDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Tour;

/**
 * This controller is responsible for the adding tour function
 *
 * @author DucTM
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class TourController extends HttpServlet {

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
            out.println("<title>Servlet TourController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TourController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("views/TravelAgent/AddNewTour.jsp").forward(request, response);
        processRequest(request, response);
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
        String name = request.getParameter("name").trim();
        String destination = request.getParameter("destination").trim();
        String type = request.getParameter("type").trim();
        float price = Float.parseFloat(request.getParameter("price"));
        Date availableFrom = Date.valueOf(request.getParameter("available_from"));
        Date availableTo = Date.valueOf(request.getParameter("available_to"));
        int tripLength = Integer.parseInt(request.getParameter("trip_length"));
        int maxQuantity = Integer.parseInt(request.getParameter("max_quantity"));
        int agentID = 3;//((User)session.getAttribute("user")).getID();

        Part file = request.getPart("image");
        String image = file.getSubmittedFileName()+System.currentTimeMillis();
        String realPath = request.getServletContext().getRealPath("images");
        file.write(realPath + "/" + image);

        String description = request.getParameter("description").trim();
        try {
            DAO dao = new TourDAO();
            dao.save(new Tour(name, type, true, destination, tripLength,
                    availableFrom, availableTo, maxQuantity, price, description, agentID, image));
        } catch (Exception ex) {
            Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect(request.getContextPath() + "/home");
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
