/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BasicDAO;
import dao.UserDAO;
import dao.impl.PaymentDAOImpl;
import dao.impl.UserDaoImpl;
import entity.Payment;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import entity.User;
import java.util.List;
import utils.Mail;

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
            int agentId = ((User) request.getSession().getAttribute("user")).getId();
            List<Payment> list = dao.get(agentId);
            request.setAttribute("list", null);
            if (!list.isEmpty()) {
                request.setAttribute("list", list);
            }
            String key = request.getParameter("key");
            if (key != null) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                if (key.equals(user.getKey())) {
                    user.setKey(String.valueOf(System.currentTimeMillis()) + Math.random() % 1000 + System.currentTimeMillis());
                    String mail = (String) session.getAttribute("newMail");
                    session.removeAttribute("newMail");
                    user.setEmail(mail);
                    UserDAO uDao = new UserDaoImpl();
                    uDao.update(user);
                    session.setAttribute("user", user);
                }
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
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
        String email = acc.getEmail();
        String newEmail = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String role = acc.getRole();
        String status = acc.getStatus();
        String key = acc.getKey();
        Date dob = Date.valueOf(request.getParameter("dob"));

        int id = acc.getId();
        try {
            UserDAO dao = new UserDaoImpl();
            User user = new User(id, username, password, fullname, dob, email, phone, role, status, key);
            if (!dao.isPhoneUnique(phone, id)) {
                request.setAttribute("message", "Duplicate phone number");
            }
            if (!dao.isEmailUnique(newEmail, id)) {
                request.setAttribute("message1", "This email already exists in the list!");
            }
            if (dao.isEmailUnique(email, id) && dao.isPhoneUnique(phone, id)) {
                dao.update(user);
                session.removeAttribute("user");
                session.setAttribute("user", user);
            }
            if (!email.equals(newEmail)) {
                Mail mail = new Mail();
                String contextPath = "http://localhost:9999/EasyTravel/"; //request.getContextPath()
                mail.sentEmail(newEmail, "Easy Travel verification mail", contextPath + "profile?key=" + key, "link");
                request.setAttribute("toast", "Stay sign in and check your new mail to verify");
                session.setAttribute("newMail", newEmail);
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
