/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Hotel;

/**
 *
 * @author Ngan Ha
 */
public class HotelDAO extends DBContext implements DAO<Hotel> {

    public HotelDAO() throws Exception {
    }

    @Override
    public List<Hotel> getAll() {
        List<Hotel> list = new ArrayList<>();
        String query = "select * from hotels";
        int id;
        int stars;
        int room_available;
        String phone;
        int agent_id;
        int location;
        Hotel hotel;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                stars = rs.getInt("stars");
                room_available = rs.getInt("room_available");
                phone = rs.getString("phone");
                agent_id = rs.getInt("agent_id");
                location = rs.getInt("location");
                hotel = new Hotel(id, stars, room_available, phone, agent_id, location);
                list.add(hotel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Hotel> get(int id) {
        String query = "select * from hotels where id=?";
        Hotel hotel = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            rs.next();

            int stars = rs.getInt("stars");
            int room_available = rs.getInt("room_available");
            String phone = rs.getString("phone");
            int agent_id = rs.getInt("agent_id");
            int location = rs.getInt("location");

            hotel = new Hotel(id, stars, room_available, phone, agent_id, location);

        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public void save(Hotel t) {
        String query = "INSERT INTO hotels (id, stars, room_available, phone, agent_id, location) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getStars());
            ps.setString(3, t.getPhone());
            ps.setInt(4, t.getRoom_available());
            ps.setInt(5, t.getAgent_id());
            ps.setInt(6, t.getLocation());

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void update(Hotel t) {
        String query = "UPDATE hotels "
                + "SET stars = ?, room_available = ?, phone = ?, agent_id = ?, location = ? "
                + "WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, t.getId());
            ps.setInt(2, t.getStars());
            ps.setString(3, t.getPhone());
            ps.setInt(4, t.getRoom_available());
            ps.setInt(5, t.getAgent_id());
            ps.setInt(6, t.getLocation());
            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Hotel t) {
        String query = "DELETE FROM hotels WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, t.getId());

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public List<Hotel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
