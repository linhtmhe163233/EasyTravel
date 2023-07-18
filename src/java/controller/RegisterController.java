/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import utils.Mail;
import dao.impl.UserDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.User;

/**
 *
 * @author linhtm
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("views/Register.jsp").forward(request, response);
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
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String fullname = request.getParameter("fullname").trim();
        String password = request.getParameter("password").trim();
        String phone = request.getParameter("phone").trim();
        String role = request.getParameter("role").trim();
        Date dob = Date.valueOf(request.getParameter("dob"));
        String key = Math.random() % 1000000 + String.valueOf(System.currentTimeMillis()) + Math.random() % 1000000;
        try {
            UserDAO dao = new UserDaoImpl();
            if (!dao.registerUsernameUnique(username)) {
                request.setAttribute("message", "Username available");
            }
            if (!dao.registerEmailUnique(email)) {
                request.setAttribute("message1", "This email already exists in the list!");
            }
            if (!dao.registerPhoneUnique(phone)) {
                request.setAttribute("message2", "Duplicate phone number");
            }
            if (dao.registerEmailUnique(email) && dao.registerPhoneUnique(phone) && dao.registerUsernameUnique(username)) {
                dao.save(new User(username, password, fullname, dob, email, phone, role, "Inactive", key));
                Mail mail = new Mail();

                String contextPath = "http://localhost:9999/EasyTravel/"; //request.getContextPath()
                mail.sentEmail(email, "Easy Travel verification mail", contextPath + "home?key=" + key, "link");
                request.setAttribute("registered", true);
                
//            request.getRequestDispatcher("views/Register.jsp").forward(request, response);
            }
            doGet(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        doGet(request, response);
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
