/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BasicDAO;
import dao.UserDAO;
import dao.impl.UserDaoImpl;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author My Laptop
 */
public class ChangepasswordController extends HttpServlet {

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
            UserDAO dao = new UserDaoImpl();

        } catch (Exception ex) {
            Logger.getLogger(ChangepasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("views/ChangePassword.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User acc = (User) session.getAttribute("user");

        String mess1 = "";
        String mess2 = "";
        String mess3 = "";
        String crpassword = request.getParameter("crpassword").trim();
        String oldpassword = acc.getPassword();
        String cfpassword = request.getParameter("cfpassword").trim();

        String password = request.getParameter("password").trim();

        int id = acc.getId();

        if (crpassword.equals(oldpassword)) {
            if (password.equals(oldpassword)) {
                mess2 = "The new password cannot be the same as the current password";
                request.setAttribute("mess2", mess2);

                request.getRequestDispatcher("views/ChangePassword.jsp").forward(request, response);
            } else {

                if (cfpassword.equals(password)) {

                    try {
                        UserDAO dao = new UserDaoImpl();

                        acc.setPassword(password);
                        dao.update(acc);
                        session.removeAttribute("user");
                        session.setAttribute("user", acc);
                    } catch (Exception ex) {
                    }
                } else {
                    mess3 = "Confirm the password and the new password is not the same";
                    request.setAttribute("mess3", mess3);

                    request.getRequestDispatcher("views/ChangePassword.jsp").forward(request, response);

                }
            }

        } else {
            mess1 = "Current password is incorrect";
            request.setAttribute("mess1", mess1);

            request.getRequestDispatcher("views/ChangePassword.jsp").forward(request, response);
        }
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
