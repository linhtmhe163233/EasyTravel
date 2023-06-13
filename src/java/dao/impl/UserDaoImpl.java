/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * ??-??-2023      1.0                 LinhTM          First Implement
 * 06-06-2023      1.0                 DucTM           Fix database connection
 */
package dao.impl;

import dao.BasicDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Hotel;
import entity.User;
/**
 *
 * @author LinhTM
 */
public class UserDaoImpl extends DBContext implements BasicDAO<User> {

    public UserDaoImpl() throws Exception {

    }
    
    public User checkLogin(String username, String password) throws Exception {
        Connection conn = super.getConnection();
        String login = "SELECT * FROM users WHERE account_name=? AND password=?";
 
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(login);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
            }
        } catch (SQLException e) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public List<User> getAll() throws Exception {
        Connection conn = super.getConnection();
        List<User> list = new ArrayList();
        String sql = "select * from users";

        int id;
        String username;
        String password;
        String fullname;
        Date dob;
        String email;
        String phone;
        String role;
        String status;
        String key;

        User user;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                username = rs.getString("account_name");
                fullname = rs.getString("full_name");
                password = rs.getString("password");
                dob = rs.getDate("DOB");
                email = rs.getString("email");
                phone = rs.getString("phone");
                role = rs.getString("role");
                status = rs.getString("status");
                key = rs.getString("key");

                user = new User(id, username, password, fullname, dob, email, phone, role, status, key);
                list.add(user);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<User> get(int key) {
//        String query = "select * from users where key=?";
//
//        int id;
//        String username;
//        String password;
//        String fullname;
//        Date dob;
//        String email;
//        String phone;
//        String role;
//        String status;
//        
//        List<User> list = new ArrayList();
//        User user;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setInt(1, key);
//
//            rs = ps.executeQuery();
//            while (rs.next()) {
//
//                id = rs.getInt("id");
//                username = rs.getString("account_name");
//                fullname = rs.getString("full_name");
//                password = rs.getString("password");
//                dob = rs.getDate("DOB");
//                email = rs.getString("email");
//                phone = rs.getString("phone");
//                role = rs.getString("role");
//                status = rs.getString("status");
//                
//                user = new User(id, username, password, fullname, dob, email, phone, role, status, "");
//                list.add(user);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            super.close(conn, ps, rs);
//        }
        return null;
    }

    @Override
    public void save(User t) throws Exception {
        Connection conn = super.getConnection();
        String sql = "INSERT INTO users(account_name, email, password, full_name, phone, status, role, DOB, [key])"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, t.getUsername());
            ps.setString(2, t.getEmail());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getFullname());
            ps.setString(5, t.getPhone());
            ps.setString(6, t.getStatus());
            ps.setString(7, t.getRole());
            ps.setDate(8, t.getDob());
            ps.setString(9, t.getKey());

            ps.execute();
        } catch (SQLException ex) {
            throw new Exception("Unable to create new user");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void update(User t) throws Exception {
        Connection conn = super.getConnection();
        String query = "update users set [key]=?, status=? where id=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getKey());
            ps.setString(2, t.getStatus());
            ps.setInt(3, t.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Unable to update data to database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void delete(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> search(String keyword) throws Exception {
        Connection conn = super.getConnection();
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE full_name like ? or [key]=?";

        int id;
        String username;
        String password;
        String fullname;
        Date dob;
        String email;
        String phone;
        String role;
        String status;
        String key;

        User user;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, keyword);
            
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                username = rs.getString("account_name");
                fullname = rs.getString("full_name");
                password = rs.getString("password");
                dob = rs.getDate("DOB");
                email = rs.getString("email");
                phone = rs.getString("phone");
                role = rs.getString("role");
                status = rs.getString("status");
                key = rs.getString("key");

                user = new User(id, username, password, fullname, dob, email, phone, role, status, key);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new Exception("Unable to get data to database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;

    }
}
