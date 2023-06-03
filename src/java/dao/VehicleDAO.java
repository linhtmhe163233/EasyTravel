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
import models.Vehicle;

/**
 *
 * @author SHD
 */
public class VehicleDAO extends DBContext implements DAO<Vehicle> {

    public VehicleDAO() throws Exception {
    }

    @Override
    public List<Vehicle> getAll() {
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
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    public void save(String type, String phone, String name, int pass) throws Exception {
        String query = "INSERT INTO [dbo].[vehicles]([type],[driver_name],[driver_phone],[max_passengers]) VALUES (?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, type);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setInt(4, pass);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, null);
        }
    }

    public List<String> getAllPhones() {
        List<String> list = new ArrayList<>();
        String query = "select vehicles.driver_phone from vehicles";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Vehicle> get(int agentid) {
        List<Vehicle> list = new ArrayList<>();
        String query = "select * from vehicles where agentid=?";

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
            Logger.getLogger(Vehicle.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public void save(Vehicle t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
