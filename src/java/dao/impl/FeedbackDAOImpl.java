/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.FeedbackDAO;
import entity.Booking;
import entity.FeedbackThread;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Pagination;

/**
 *
 * @author My Laptop
 */
public class FeedbackDAOImpl extends DBContext implements FeedbackDAO {

    @Override
    public List getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FeedbackThread> get(int tourID) throws Exception {
        List<FeedbackThread> list = new ArrayList<>();
        String query = "select * from feedback_threads where tour_id=?";

        int ID;
        int rating;
        Timestamp time;
        String content;
        int touristID;
//        int tourID;

        FeedbackThread feedback;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, tourID);

            rs = ps.executeQuery();
            while (rs.next()) {
                ID = rs.getInt("id");
                rating = rs.getInt("rating");
                time = rs.getTimestamp("time");
                content = rs.getString("content");
                touristID = rs.getInt("tourist_id");
                tourID = rs.getInt("tour_id");
                feedback = new FeedbackThread(ID, rating, time, content, touristID, tourID);

                list.add(feedback);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(FeedbackThread t) throws Exception {
        String query = "insert into feedback_threads(rating, time, [content], tourist_id, tour_id)"
                + "values(?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, t.getRating());
            ps.setTimestamp(2, t.getTime());
            ps.setString(3, t.getContent());
            ps.setInt(4, t.getTouristID());
            ps.setInt(5, t.getTourID());

            ps.execute();
        } catch (SQLException ex) {
            throw new Exception("Unable to save data to database");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public List search(String keyword) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        try {
//            User u = new User(2, "agent_demo", "123456", "Nguyen Van Ban", Date.valueOf("2003-01-01"), "ANV345@gmail.com", "0123456781", "Travel Agent", "Active", null);
            FeedbackDAOImpl ud = new FeedbackDAOImpl();
//            System.out.println(ud.get(1));

//            ud.getPage(new Pagination(2,10,1),1);
            List<FeedbackThread> a = ud.getPage(new Pagination(2, 10, 1), 1);
           

//            System.out.println(a.get(0));
//
//            System.out.println(ud.getTotalItems(1));
//            ud.update(u);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(FeedbackThread t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(FeedbackThread t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FeedbackThread> getPage(Pagination page, int tourID) throws Exception {
        List<FeedbackThread> list = new ArrayList<>();
        String query = "SELECT feedback_threads.id, rating, content, tourist_id, time, full_name \n"
                + " FROM feedback_threads\n"
                + " FULL OUTER JOIN users\n"
                + " ON feedback_threads.tourist_id = users.id \n"
                + " where tour_id = ? order by feedback_threads.time desc offset ? rows fetch next ? rows only";
        int id;
        int rating;
        Timestamp time;
        String content;
        int touristID;
        String fullName;
        FeedbackThread feedback;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, tourID);
            ps.setInt(2, page.getOffset());
            ps.setInt(3, page.getItemsPerPage());

            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                rating = rs.getInt("rating");
                time = rs.getTimestamp("time");
                content = rs.getString("content");
                touristID = rs.getInt("tourist_id");
                fullName = rs.getString("full_name");

                feedback = new FeedbackThread(id, rating, time, content, touristID, tourID, fullName);

                list.add(feedback);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public int getTotalItems(int tourID) throws Exception {
        String query = "select count(*) from feedback_threads where tour_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            ps = conn.prepareStatement(query);
            ps.setInt(1, tourID);
            rs = ps.executeQuery();

            rs.next();
            return rs.getInt(1);

        } catch (Exception e) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }
    
   public Booking checkDone(int touristID, int tourID) throws Exception {
        Connection conn = super.getConnection();
        String sql = "SELECT * FROM booking WHERE status='done' and tourist_id =? and tour_id = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, touristID);
            ps.setInt(2, tourID);

            rs = ps.executeQuery();
            while (rs.next()) {
                return new Booking(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getTimestamp(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getString(8));
         
            }
        } catch (SQLException e) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return null;
    }

}
