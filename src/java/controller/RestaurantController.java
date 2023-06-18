package controller;

import dao.BasicDAO;
import dao.impl.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Restaurant;
import entity.Vehicle;
import java.sql.Connection;

/**
 *
 * @author DUCHIEUPC.COM
 */
public class RestaurantController extends DBContext implements BasicDAO<Restaurant>{

//    public List<Restaurant> getAll(String search, int index, int pagesize) {
//        List<Restaurant> list = new ArrayList<>();
//        String query = "select * from restaurants r join Users u on r.agent_id = u.id\n"
//                + "where r.[type] like '%"+search+"%' or r.phone like '%"+search+"%' \n"
//                + "order by r.id asc\n"
//                + " OFFSET "+(index - 1) * 9+" ROWS FETCH NEXT "+pagesize+"  ROWS ONLY";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            DBContext db = new DBContext();
//            ps = db.getConnection().prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Restaurant v = new Restaurant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
//                v.setAgentName(rs.getString("full_name"));
//                list.add(v);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//
//        }
//        return list;
//    }

//    public List<User> getAllAgent() {
//        List<User> list = new ArrayList<>();
//        String query = "SELECT TOP (1000) [id],[full_name] FROM [EasyTravel].[dbo].[users]\n"
//                + "where [role] = 'Travel Agent'";
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            DBContext db = new DBContext();
//            ps = db.getConnection().prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                User v = new User(rs.getInt(1), rs.getString(2));
//                list.add(v);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//
//        }
//        return list;
//    }

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
    public List<Restaurant> getAll() throws Exception {
       Connection conn = null;
        List<Restaurant> list = new ArrayList<>();
        String query = "select * from restaurant";

        PreparedStatement ps = null;
        ResultSet rs = null;
Restaurant restaurant;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                restaurant  = new Restaurant(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
                list.add(restaurant);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list; 
    }

    @Override
    public List<Restaurant> get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Restaurant t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Restaurant t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Restaurant t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Restaurant> search(String keyword) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}