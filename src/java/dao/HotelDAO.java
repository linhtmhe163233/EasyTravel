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
        String name;
        int stars;
        int room_available;
        String phone;
        int agent_id;
        String location;

        Hotel hotel;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                stars = rs.getInt("stars");
                room_available = rs.getInt("room_available");
                phone = rs.getString("phone");
                agent_id = rs.getInt("agent_id");
                location = rs.getString("location");
                hotel = new Hotel(name, stars, room_available, phone, agent_id, location);
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
    public List<Hotel> get(int agentID) {
        String query = "select * from hotels where agent_id=?";
        List<Hotel> list = new ArrayList();
        Hotel hotel;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, agentID);

            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int stars = rs.getInt("stars");
                int room_available = rs.getInt("room_available");
                String phone = rs.getString("phone");
                int agent_id = rs.getInt("agent_id");
                String location = rs.getString("location");

                hotel = new Hotel(name, stars, room_available, phone, agent_id, location);
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
    public void save(Hotel t) {
        String query = "INSERT INTO hotels (name, stars, room_available, phone, agent_id, location) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getName());
            ps.setInt(2, t.getStars());
            ps.setInt(3, t.getRoom_available());
            ps.setString(4, t.getPhone());
            ps.setInt(5, t.getAgent_id());
            ps.setString(6, t.getLocation());

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
                + "SET name = ?, stars = ?, room_available = ?, phone = ?, agent_id = ?, location = ? "
                + "WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getName());
            ps.setInt(2, t.getStars());
            ps.setString(3, t.getPhone());
            ps.setInt(4, t.getRoom_available());
            ps.setInt(5, t.getAgent_id());
            ps.setString(6, t.getLocation());
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
