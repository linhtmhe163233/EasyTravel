/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import commonutils.SendMail;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

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
//        processRequest(request, response);
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String cfpassword = request.getParameter("cfpassword");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        Date dob = Date.valueOf(request.getParameter("dob"));
        
        try{
        UserDao dao = new UserDao();
        dao.save(new User(username, password, fullname, dob, email, phone, role, email));
        
        SendMail mail = new SendMail();
        String pass = mail.createCaptcha();
        int status = 1;
        String messpass = "";
        if (!password.equals(cfpassword)) {
            messpass = "Password and confirm password is incorrect";
//        }else{
//            Users u = d.checkUserExist(email);
//            if (u != null) {
//                request.setAttribute("notif", "<div class=\"alert alert-danger\" role=\"alert\">\n"
//                        + "Email taken!\n"
//                        + "</div>");
//                request.getRequestDispatcher("register.jsp").forward(request, response);
//            } else {
//
//                String encryptedpw = d.encryptPassword(password);
////                Users a = new Users(encryptedpw, email, timeNow, cfToken, timeNow, false, false, isRole);
//                d.register(encryptedpw, email, timeNow, timeNow, Integer.parseInt(isRoleString));
//                
//                //Send data to register.jsp
//                request.setAttribute("notif", "<div class=\"alert alert-danger\" role=\"alert\">\n"
//                        + "Confirmation link has been send to your email. Please check it!\n"
//                        + "</div>");
//                request.getRequestDispatcher("register.jsp").forward(request, response);
//        }
        }

        request.setAttribute("messpass",messpass);
        request.setAttribute("username",username);
        request.getRequestDispatcher("views/Register.jsp").forward(request, response);
        
         } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
