/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.BasicDAO;
import entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DUCHIEUPC.COM
 */
public class RestaurantDAOlmpl extends DBContext implements BasicDAO<Restaurant> {

    public List<CustomRestaurant> getAll(String search, int index, int pagesize) throws Exception {
        List<CustomRestaurant> list = new ArrayList<>();
        String query = "select * from restaurants r join Users u on r.agent_id = u.id\n"
                + "where r.[type] like '%" + search + "%' or r.phone like '%" + search + "%' \n"
                + "order by r.id asc\n"
                + " OFFSET " + (index - 1) * 9 + " ROWS FETCH NEXT " + pagesize + "  ROWS ONLY";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                CustomRestaurant v = new CustomRestaurant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
                v.setAgentName(rs.getString("full_name"));
                list.add(v);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }
        return list;

    }

    public List<User> getAllAgent() {
        List<User> list = new ArrayList<>();
        String query = "SELECT TOP (1000) [id],[full_name] FROM [EasyTravel].[dbo].[users]\n"
                + "where [role] = 'Travel Agent'";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            DBContext db = new DBContext();
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                User v = new User(rs.getInt(1), rs.getString(2));
                list.add(v);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return list;
    }

    @Override
    public List<Restaurant> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public CustomRestaurant getDetail(int id) throws Exception {
        String query = "select * from restaurants where id = " + id;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            DBContext db = new DBContext();
            ps = db.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                 CustomRestaurant v = new CustomRestaurant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
                return v;
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
        
        
    }

    @Override
    public void save(Restaurant t) throws Exception {
        String query = "INSERT INTO [dbo].[restaurants]   ([type] ,[table_available] ,[phone],[agent_id])\n"
                + "     VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        Connection conn = new DBContext().getConnection();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getType());
            ps.setInt(2, t.getTableAvailable());
            ps.setString(3, t.getPhone());
            ps.setInt(4, t.getAgentId());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(conn, ps, null);
        }
    }

    public void update(String type, String table, String phone, String tid) throws Exception {
        String query = "UPDATE [dbo].[restaurants]\n"
                + "   SET [type] = ?\n"
                + "      ,[table_available] = ?\n"
                + "      ,[phone] = ?\n"
                + " WHERE id = " + tid;

        PreparedStatement ps = null;

        try {
            DBContext db = new DBContext();
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, type);
            ps.setString(2, table);
            ps.setString(3, phone);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int tid) throws Exception {
        String query = "Delete [restaurants]  where id =" + tid;

        PreparedStatement ps = null;

        try {
            DBContext db = new DBContext();
            ps = db.getConnection().prepareStatement(query);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> search(String keyword) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void add(String type, String table, String phone, int aId) {
        String query = "INSERT INTO [dbo].[restaurants]   ([type] ,[table_available] ,[phone],[agent_id])\n"
                + "     VALUES (?,?,?,?)";

        PreparedStatement ps = null;

        try {
            DBContext db = new DBContext();
            ps = db.getConnection().prepareStatement(query);
            ps.setString(1, type);
            ps.setString(2, table);
            ps.setString(3, phone);
            ps.setInt(4, aId);

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Restaurant t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Restaurant t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Restaurant> get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
