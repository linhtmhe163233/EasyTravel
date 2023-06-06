/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * ??-??-2023      1.0                 DungMQ           First Implement
 * 03-06-2023      1.0                 DucTM            Fix getAll(), get(), save() method
 * 06-06-2023      1.0                 DucTM            Fix database connection
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Vehicle;

/**
 *
 * @author SHD
 */
public class VehicleDAO extends DBContext implements DAO<Vehicle> {

    public VehicleDAO() throws Exception {
    }

    @Override
    public List<Vehicle> getAll() throws Exception {
        Connection conn = super.getConnection();
        List<Vehicle> list = new ArrayList<>();
        String query = "select * from vehicles";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
                list.add(v);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public void save(Vehicle vehicle) throws Exception {
        Connection conn = super.getConnection();
        String query = "INSERT INTO [dbo].[vehicles]([type],[driver_name],[driver_phone],[max_passengers],[agent_id])"
                + "VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, vehicle.getType());
            ps.setString(2, vehicle.getDriverName());
            ps.setString(3, vehicle.getDriverPhone());
            ps.setInt(4, vehicle.getMaxPassenger());
            ps.setInt(5, vehicle.getAgentID());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public List<Vehicle> get(int agentid) throws Exception {
        Connection conn = super.getConnection();
        List<Vehicle> list = new ArrayList<>();
        String query = "select * from vehicles where agent_id=?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, agentid);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
                list.add(v);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public void update(Vehicle t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Vehicle t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Vehicle> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
