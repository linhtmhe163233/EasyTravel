/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BasicDAO;
import dao.UserDAO;
import dao.impl.PaymentDAOImpl;
import dao.impl.UserDaoImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.User;

/**
 *
 * @author My Laptop
 */
public class ProfileController extends HttpServlet {

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
            BasicDAO dao = new PaymentDAOImpl();
            int id = ((User)request.getSession().getAttribute("user")).getId();
            request.setAttribute("payment", dao.get(id).get(0));
        } catch (Exception ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("views/Profile.jsp").forward(request, response);
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

        String username = acc.getUsername();
        String fullname = request.getParameter("fullname").trim();
        String password = acc.getPassword();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String role = acc.getRole();
        String status = acc.getStatus();
        String key = acc.getKey();
        Date dob = Date.valueOf(request.getParameter("dob"));
//        int id = Integer.parseInt(request.getParameter("id"));
        int id = acc.getId();
        getServletContext().log(username + fullname + password + email + phone + role + status + key + dob + id);
        try {
            UserDAO dao = new UserDaoImpl();
//            request.setAttribute("user", new User(username, password, fullname, dob, email, phone, role, status, key));
            User user = new User(id, username, password, fullname, dob, email, phone, role, status, key);
            if (!dao.isPhoneUnique(phone, id)) {
                request.setAttribute("message", "Duplicate phone number");
            }
            if (!dao.isEmailUnique(email, id)) {
                request.setAttribute("message1", "This email already exists in the list!");
            }
//            else{
//                request.setAttribute("message2", "Please check your email for confirmation");
//            }
            if (dao.isEmailUnique(email, id) && dao.isPhoneUnique(phone, id)) {
                dao.update(user);
                session.removeAttribute("user");
                session.setAttribute("user", user);
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);

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
