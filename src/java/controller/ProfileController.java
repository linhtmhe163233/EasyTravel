/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BasicDAO;
import dao.impl.UserDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.User;

/**
 *
 * @author My Laptop
 */
public class ProfileController extends HttpServlet {

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
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
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
        try {
            
            BasicDAO dao = new UserDaoImpl();
//            HttpSession session = request.getSession();
//            User acc = (User) session.getAttribute("user");
////            int id = ((User) session.getAttribute("user")).getId();
////            System.out.println(id);
////            List<User> list = dao.get(id);
//            request.setAttribute("list", acc);
            
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
        String fullname = request.getParameter("fullname");
        String password = acc.getPassword();
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = acc.getRole();
        String status = acc.getStatus();
        String key = acc.getKey();
        Date dob = Date.valueOf(request.getParameter("dob"));
//        int id = Integer.parseInt(request.getParameter("id"));
        int id = acc.getId();
        getServletContext().log(username + fullname + password + email + phone + role + status + key + dob + id);
        try {
            BasicDAO dao = new UserDaoImpl();
//            request.setAttribute("user", new User(username, password, fullname, dob, email, phone, role, status, key));
            User user = new User(id, username, password, fullname, dob, email, phone, role, status, key);
            dao.update(user);
            session.removeAttribute("user");
            session.setAttribute("user", user);
        } catch (Exception ex) {
//            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex

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
