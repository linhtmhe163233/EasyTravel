package dao.impl;

import dao.BasicDAO;
import entity.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BookingDAOImpl extends DBContext implements BasicDAO<Booking> {

    public BookingDAOImpl() throws Exception {
    }

    @Override
    public List<Booking> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            conn=getConnection();
            ps=conn.prepareStatement(sql);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Booking t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Booking> search(String keyword) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
