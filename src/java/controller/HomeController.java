/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 27-05-2023      1.0                 DucTM           First Implement
 * 13-06-2023      1.0                 DucTM           Update paging
 */
package controller;

import utils.Pagination;
import dao.impl.TourDAOImpl;
import dao.impl.UserDaoImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import entity.Tour;
import entity.User;
import dao.BasicDAO;
import dao.TourDAO;

/*
 * This class controls the home page of the website
 * 
 * @author DucTM
 */
public class HomeController extends HttpServlet {

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
        String key = request.getParameter("key");
        if (key != null) {
            try {
                BasicDAO dao = new UserDaoImpl();
                List<User> list = dao.search(key);
                if (list.isEmpty() || !list.get(0).getStatus().equals("Inactive")) {
                    request.getRequestDispatcher("views/LandingPage.jsp").forward(request, response);
                } else {
                    User user = list.get(0);
                    user.setStatus("Active");
                    user.setKey(String.valueOf(System.currentTimeMillis()) + Math.random() % 1000 + System.currentTimeMillis());
                    dao.update(user);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    request.removeAttribute("key");
                }
            } catch (Exception ex) {
                request.setAttribute("error", ex.getMessage());
                request.getRequestDispatcher("views/Error.jsp").forward(request, response);
            }
        }
        TourDAO dao;
        try {
            dao = new TourDAOImpl();
            
            int totalItems = dao.getTotalItems();
            
            Object indexObj = request.getAttribute("index");
            int index;
            if (indexObj == null) {
                index = 1;
            } else {
                index = (int) indexObj;
            }
            
            Pagination page = new Pagination(totalItems, 3, index);
            List<Tour> list = dao.getPage(page);
            
            request.setAttribute("page", page);
            request.setAttribute("list", list);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
        }
        request.getRequestDispatcher("views/HomePage.jsp").forward(request, response);
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
