/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * ??-??-2023      1.0                 HaPN            First Implement
 * 04-06-2023      1.0                 DucTM           Implement get() and save() 
 * 06-06-2023      1.0                 DucTM           Fix database connection
 */
package dao.implement;

import dao.BasicDAO;
import dao.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Hotel;

/**
 *
 * @author Ngan Ha
 */
public class HotelDAOImpl extends DBContext implements BasicDAO<Hotel> {

    public HotelDAOImpl() throws Exception {
    }
    @Override
    public List<Hotel> getAll() throws Exception {
        Connection conn = super.getConnection();
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
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Hotel> get(int agentID) throws Exception {
        Connection conn = super.getConnection();
        List<Hotel> list = new ArrayList();
        String query = "select * from hotels where agent_id=?";
        
        int id;
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
            ps.setInt(1, agentID);
            rs = ps.executeQuery();
            while (rs.next()) {
                id=rs.getInt("id");
                name = rs.getString("name");
                stars = rs.getInt("stars");
                room_available = rs.getInt("room_available");
                phone = rs.getString("phone");
                agent_id = rs.getInt("agent_id");
                location = rs.getString("location");
                hotel = new Hotel(id, name, stars, room_available, phone, agent_id, location);
                list.add(hotel);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public void save(Hotel t) throws Exception {
        Connection conn = super.getConnection();
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
            throw new Exception("Unable to save data to database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void update(Hotel t) throws Exception {
        Connection conn = super.getConnection();
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
            throw new Exception("Unable to update data to database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Hotel t) throws Exception {
        Connection conn = super.getConnection();
        String query = "DELETE FROM hotels WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, t.getId());

            ps.execute();

        } catch (SQLException ex) {
            throw new Exception("Unable to delete data from database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public List<Hotel> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
