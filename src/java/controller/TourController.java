/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 29-05-2023      1.0                 DucTM           First Implement
 * 30-05-2023      1.0                 DucTM           Add save image
 */
package controller;

import dao.impl.TourDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.sql.Date;
import entity.Tour;
import entity.User;
import dao.BasicDAO;

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
        String act = request.getParameter("act");
        if (act != null && act.equals("update")) {
            Tour tour = (Tour) request.getSession().getAttribute("tour");
            request.setAttribute("tour", tour);
            request.getSession().removeAttribute("tour");
        }
        request.getRequestDispatcher("views/TravelAgent/AddNewTour.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String name = request.getParameter("name").trim();
        String destination = request.getParameter("destination").trim();
        String type = request.getParameter("type").trim();
        float price = Float.parseFloat(request.getParameter("price"));
        Date availableFrom = Date.valueOf(request.getParameter("available_from"));
        Date availableTo = Date.valueOf(request.getParameter("available_to"));
        int tripLength = Integer.parseInt(request.getParameter("trip_length"));
        int maxQuantity = Integer.parseInt(request.getParameter("max_quantity"));
        HttpSession session = request.getSession();
        int agentID = ((User) session.getAttribute("user")).getId();

        String description = request.getParameter("description").trim();
        try {
            Part file = request.getPart("image");
            String image = System.currentTimeMillis() + file.getSubmittedFileName();
            String realPath = request.getServletContext().getRealPath("images");
            file.write(realPath + "/" + image);
            
            BasicDAO dao = new TourDAOImpl();
            if (id != null && !id.isEmpty()) {
                dao.update(new Tour(Integer.parseInt(id), name, type, true, destination, tripLength,
                        availableFrom, availableTo, maxQuantity, price, description, agentID, image));
                response.sendRedirect(request.getContextPath() + "/tour?id=" + id);
            } else {
                dao.save(new Tour(name, type, true, destination, tripLength,
                        availableFrom, availableTo, maxQuantity, price, description, agentID, image));
                response.sendRedirect(request.getContextPath() + "/home");
            }
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
