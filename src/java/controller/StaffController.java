/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 31-05-2023      1.0                 DucTM           First Implement
 */
package controller;

import dao.impl.StaffDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import entity.Staff;
import entity.User;
import dao.StaffDAO;
import utils.Pagination;


/*
 * This class controls the staff list and perform CRUD functions
 * 
 * @author DucTM
 */
public class StaffController extends HttpServlet {

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
            StaffDAO dao = new StaffDAOImpl();
            HttpSession session = request.getSession();

            Object agentObj = session.getAttribute("user");
            if (agentObj == null) {
                throw new Exception("Session is not valid");
            }
            int agentId = ((User) agentObj).getId();

            int totalItems = dao.getTotalItems(agentId);

            Object indexObj = request.getAttribute("index");
            int index;
            if (indexObj == null) {
                index = 1;
            } else {
                index = (int) indexObj;
            }

            Pagination page = new Pagination(totalItems, 10, index);
            List<Staff> list = dao.getPageByAgent(agentId, page);

            request.setAttribute("page", page);
            request.setAttribute("list", list);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("views/Error.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("views/TravelAgent/StaffList.jsp").forward(request, response);
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
        if (request.getParameter("add") != null) {
            String name = request.getParameter("name").trim();
            Date DOB = Date.valueOf(request.getParameter("DOB"));
            String phone = request.getParameter("phone").trim();
            boolean gender = request.getParameter("gender").equals("Male");
            int agentId = 0;
            HttpSession session = request.getSession();

            try {
                StaffDAO dao = new StaffDAOImpl();
                Object agentObj = session.getAttribute("user");
                if (agentObj == null) {
                    throw new Exception("Session is not valid");
                }
                agentId = ((User) agentObj).getId();
                Staff staff = new Staff(name, DOB, phone, gender, agentId);
                if (dao.isPhoneUnique(phone)) {
                    dao.save(staff);
                } else {
                    request.setAttribute("staff", staff);
                    request.setAttribute("message", "This phone number already exists in the list!");
                }
            } catch (Exception ex) {
                request.setAttribute("error", ex.getMessage());
                request.getRequestDispatcher("views/Error.jsp").forward(request, response);
                return;
            }
        }
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
