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
package dao.impl;

import dao.BasicDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Hotel;

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

    public List<Hotel> getAll2() throws Exception {
        Connection conn = super.getConnection();
        List<Hotel> list = new ArrayList<>();
        String query = "select * from hotels";

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
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
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
    public List<Hotel> get(int id) throws Exception {
        Connection conn = super.getConnection();
        List<Hotel> list = new ArrayList();
        String query = "select * from hotels where id=?";

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
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                stars = rs.getInt("stars");
                room_available = rs.getInt("room_available");
                phone = rs.getString("phone");
                location = rs.getString("location");
                hotel = new Hotel(name, stars, room_available, phone, location);
                list.add(hotel);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    public Hotel getHotelById(int hotelId) throws Exception {
        Connection conn = super.getConnection();
        String query = "select * from hotels where id=?";
        Hotel hotel = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, hotelId);
            rs = ps.executeQuery();
            while (rs.next()) {
                hotel = new Hotel();
                hotel.setId(rs.getInt("id"));
                hotel.setName(rs.getString("name"));
                hotel.setName(rs.getString("name"));
                hotel.setStars(rs.getInt("stars"));
                hotel.setRoom_available(rs.getInt("room_available"));
                hotel.setPhone(rs.getString("phone"));
                hotel.setLocation(rs.getString("location"));
               
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return hotel;
    }

    @Override
    public void save(Hotel t) throws Exception {
        Connection conn = super.getConnection();
        String query = "INSERT INTO hotels (name, stars, room_available, phone, location) "
                + "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getName());
            ps.setInt(2, t.getStars());
            ps.setInt(3, t.getRoom_available());
            ps.setString(4, t.getPhone());
            ps.setString(5, t.getLocation());

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

    public void updateHotle(int id, String name, int stars, int room_available, String phone, String location) throws Exception {
        Connection conn = super.getConnection();
        String query = "UPDATE hotels "
                + "SET name = ?, stars = ?, room_available = ?, phone = ?, location = ? "
                + "WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, stars);
            ps.setInt(3, room_available);
            ps.setString(4, phone);
            ps.setString(5, location);
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Unable to update data to database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    public void deleteHotel(int id) throws Exception {
        Connection conn = super.getConnection();
        String query = "Delete from hotels where id = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new Exception("Unable to delete data from database");
        } finally {
            super.close(conn, ps, null);
        }
    }

}
