/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Vehicle;

/**
 *
 * @author SHD
 */
public class VehicleDAO extends DBContext1 {

    public List<Vehicle> getAll() {
        List<Vehicle> list = new ArrayList<>();
        String query = "select * from vehicles";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
                list.add(v);
            }
        } catch (SQLException ex) {

        }
        return list;
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(new VehicleDAO().getAll().get(0).getDriver_name());
    }

    public void add(String type, String phone, String name, int pass) {
        String query = "INSERT INTO [dbo].[vehicles]([type],[driver_name],[driver_phone],[max_passengers]) VALUES (?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, type);
            ps.setString(2, name);
            ps.setString(3, phone);
            ps.setInt(4, pass);

            ps.executeUpdate();

        } catch (SQLException ex) {

        } 
    }
    public List<String> getAllPhones() {
        List<String> list = new ArrayList<>();
        String query = "select vehicles.driver_phone from vehicles";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {

        }
        return list;
    }

}
