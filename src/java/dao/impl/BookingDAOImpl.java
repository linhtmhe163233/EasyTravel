package dao.impl;

import dao.BookingDAO;
import entity.Booking;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.Pagination;

public class BookingDAOImpl extends DBContext implements BookingDAO {

    public BookingDAOImpl() throws Exception {
    }

    @Override
    public List<Booking> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Booking> get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Booking t) throws Exception {
        String sql = "INSERT INTO booking(tourist_id, tour_id, start_date, "
                + "tourists_quantity, status, note, book_time)"
                + "VALUES(?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getTouristId());
            ps.setInt(2, t.getTourId());
            ps.setDate(3, t.getStartDate());
            ps.setInt(4, t.getTouristsQuantity());
            ps.setString(5, t.getStatus());
            ps.setString(6, t.getNote());
            ps.setTimestamp(7, t.getBookTime());

            ps.execute();
        } catch (Exception e) {
            throw new Exception("Unable to book this tour now, try again later!");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public void update(Booking t) throws Exception {
        String query="update booking set status=?, reason=? where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn=getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, t.getStatus());
            ps.setString(2, t.getReason());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Can't process this request now, try again later!");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public void delete(Booking t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Booking> search(String keyword) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Booking> getTourHistory(int touristId, Pagination page) throws Exception {
        String sql = "select booking.id, tour_id, book_time, start_date, tourists_quantity, booking.status, note, name, reason  "
                + "from booking join tours "
                + "on tour_id=tours.id where tourist_id=? "
                + "order by book_time desc "
                + "offset ? rows fetch next ? rows only";
        List<Booking> list = new ArrayList();
        int id;
        int tourId;
        Timestamp bookTime;
        Date startDate;
        int touristsQuantity;
        String status;
        String note;
        String tourName;
        String reason;
        Booking booking = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, touristId);
            ps.setInt(2, page.getOffset());
            ps.setInt(3, page.getItemsPerPage());
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                tourId = rs.getInt("tour_id");
                bookTime = rs.getTimestamp("book_time");
                startDate = rs.getDate("start_date");
                touristsQuantity = rs.getInt("tourists_quantity");
                status = rs.getString("status");
                note = rs.getString("note");
                tourName = rs.getString("name");
                reason = rs.getString("reason");
                booking = new Booking(id, touristId, tourId, bookTime, startDate, 
                        touristsQuantity, status, note, tourName, reason);
                list.add(booking);
            }
        } catch (Exception e) {
            throw new Exception("Unable to get data from database!");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }
    public static void main(String[] args) throws Exception {
        BookingDAOImpl dao = new BookingDAOImpl();
        Pagination page = new Pagination(1, 10, 1);
        dao.getTourHistory(1, page);
    }
    @Override
    public List<Booking> getBookingList(int agentId, Pagination page) throws Exception {
        String sql = "select booking.id, tour_id, book_time, start_date, tourists_quantity, booking.status, "
                + "note, name, tourist_id, full_name, phone, email, trip_length, reason "
                + "from booking "
                + "join users on tourist_id=users.id "
                + "join tours on tour_id=tours.id "
                + "where agent_id=? "
                + "order by book_time desc "
                + "offset ? rows fetch next ? rows only";
        List<Booking> list = new ArrayList();
        int id;
        int touristId;
        int tourId;
        Timestamp bookTime;
        Date startDate;
        int touristsQuantity;
        String status;
        String note;
        String tourName;
        String touristName;
        String touristPhone;
        String touristEmail;
        int tourLength;
        String reason;
        Booking booking = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, agentId);
            ps.setInt(2, page.getOffset());
            ps.setInt(3, page.getItemsPerPage());
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                touristId = rs.getInt("tourist_id");
                tourId = rs.getInt("tour_id");
                bookTime = rs.getTimestamp("book_time");
                startDate = rs.getDate("start_date");
                touristsQuantity = rs.getInt("tourists_quantity");
                status = rs.getString("status");
                note = rs.getString("note");
                tourName = rs.getString("name");
                touristName = rs.getString("full_name");
                touristPhone = rs.getString("phone");
                touristEmail = rs.getString("email");
                tourLength = rs.getInt("trip_length");
                reason = rs.getString("reason");
                booking = new Booking(id, touristId, tourId, bookTime, startDate, touristsQuantity, status, note, 
                        touristName, tourName, touristPhone, touristEmail, tourLength, reason);
                list.add(booking);
            }
        } catch (Exception e) {
            throw new Exception("Unable to get data from database!");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }
    
    @Override
    public int getTotalItems(int searchBy, String type) throws Exception {
        String query = null;
        if (type.equals("history")) {
            query = "select count(*) from booking where tourist_id=?";
        } else if (type.equals("request")) {
            query = "select count(*) from booking join tours on tour_id=tours.id "
                    + "where tours.agent_id in (select id from users where id=?)";
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, searchBy);
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

}
