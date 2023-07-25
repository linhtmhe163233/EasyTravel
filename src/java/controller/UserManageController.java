/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import dao.impl.UserDaoImpl;
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
 * @author My Laptop
 */
public class UserManageController extends HttpServlet {

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
            UserDAO dao = new UserDaoImpl();
            int index = 1;
            String indexStr = request.getParameter("index");
            if (indexStr != null && indexStr.matches("^[0-9]+$")) {
                index = Integer.parseInt(indexStr);
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            int totalItems = dao.getTotalItems(search.trim());
            Pagination page = new Pagination(totalItems, 10, index);
            List<User> list = dao.getPage(search, page);
            System.out.println(page.getIndex());
            request.setAttribute("page", page);
            request.setAttribute("list", list);
        } catch (Exception ex) {
            Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("views/Admin/UserList.jsp").forward(request, response);
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
        String status = request.getParameter("status");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            UserDAO dao = new UserDaoImpl();
            dao.updateStatus(status, id);
            response.sendRedirect("usermanage");
        } catch (Exception ex) {
            Logger.getLogger(UserManageController.class.getName()).log(Level.SEVERE, null, ex);
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
