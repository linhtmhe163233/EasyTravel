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
import dao.TourDAO;
import dao.UserDAO;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/*
 * This class controls the home page of the website
 * 
 * @author DucTM
 */
public class HomeController extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        try {
            TourDAO dao = new TourDAOImpl();
            dao.closeOutdated();
        } catch (Exception e) {
        }
        super.init();
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
        request.setCharacterEncoding("UTF-8");
        String key = request.getParameter("key");
        if (key != null) {
            try {
                UserDAO dao = new UserDaoImpl();
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
            String search = request.getParameter("search");
            Object indexObj = request.getAttribute("index");
            int index;
            if (indexObj == null) {
                index = 1;
            } else {
                index = (int) indexObj;
            }
            if (search == null) {
                search = "";
            }
            int totalItems = dao.getTotalItems(search);
            Pagination page = new Pagination(totalItems, 6, index);
            List<Tour> list = dao.getPage(search, page);
//            if (!search.isEmpty()) {
//                System.out.println("search not null");
//                PrintWriter out = response.getWriter();
//                String link;
//                int j=0;
//                for (Tour t : list) {
//                    System.out.println(j++);
//                    link="tour?id="+t.getId();
//                    out.print("<div class=\"card rounded\" style=\"width: 18rem;\">\n");
//                    if(!t.isEnabled()&&((User)request.getSession().getAttribute("user")).getRole().equals("Tourist")){
//                        link="#";
//                    }
//                    out.print("<a href=\""+link+"\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"Click to see details\">\n");
//                    out.print("<img class=\"card-img-top border-bottom border-dark pb-4 rounded-top img-fluid\"  \n");
//                    out.print("src=\"./images/"+t.getImage()+"\" alt=\""+t.getName()+"\" style=\"max-height: 216px\">\n");
//                    out.print("</a>\n");
//                    out.print("<a href=\""+link+"\" class=\"card-body text-body card-link\"  \n");
//                    out.print("data-toggle=\"tooltip\" data-placement=\"top\" title=\"Click to see details\">\n");
//                    out.print("<h6 class=\"card-title text-truncate\">"+t.getName()+"</h6>\n");
//                    out.print("<p class=\"card-text border-bottom border-dark pb-2 text-truncate\">"+t.getDestination()+"</p>\n");
//                    out.print("<p class=\"card-text\">"+t.getDescription().substring(0, 81)+"...</p>\n");
//                    out.print("</a>\n");
//                    out.print("<div class=\"card-footer text-muted d-flex flex-row flex-wrap justify-content-between \n");
//                    out.print("align-items-center\">\n");
//                    out.print("<c:if test=\"${tour.enabled}\">\n");
//                    if(t.isEnabled())
//                        out.print("<a href=\""+link+"&book=true\" class=\"btn btn-primary\">Book now</a>\n");
//                    if(!t.isEnabled())
//                        out.print("<button type=\"button\" class=\"btn btn-danger disabled\">Tour is closed!</button>\n");
//                    out.print("<p class=\"card-text text-right\">\n");
//                    DecimalFormat df = new DecimalFormat("###,###,###");
//                    out.print(df.format(t.getPrice()));
//                    out.print("</p>\n");
//                    out.print("</div>\n");
//                    out.print("</div>");
//                }
//                return;
//            }
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
        request.setAttribute("search", request.getParameter("hiddenSearch"));
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
