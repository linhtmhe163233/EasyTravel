/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author LinhTM
 */
public class UserDao extends DBContext implements DAO<User> {

    public UserDao() throws Exception {

    }

    public User checkLogin(String username, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String login = "SELECT * FROM users WHERE account_name=? AND password=?";
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
                        rs.getString(9));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            super.close(conn, ps, rs);
        }
        return null;
    }
    
    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList();
        String sql = "select * form user";

        int id;
        String username;
        String password;
        String fullname;
        Date dob;
        String email;
        String phone;
        String role;
        String status;

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

                user = new User(id, username, password, fullname, dob, email, phone, role, status);
                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<User> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(User t) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO users(username,email, password, fullname, phone, status, role,dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = conn.prepareStatement(sql);
           
            ps.setString(1, t.getUserame());
            ps.setString(2, t.getEmail());
            ps.setString(3, t.getPassword());
            ps.setString(4, t.getFullname());
            ps.setString(5, t.getPhonenumber());
            ps.setString(6, t.getStatus());
            ps.setString(7, t.getRole());
            ps.setDate(8, t.getDob());

        ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> search(String keyword) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE full_name like ?";

        int id;
        String username;
        String password;
        String fullname;
        Date dob;
        String email;
        String phone;
        String role;
        String status;

        User user;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
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

                user = new User(id, username, password, fullname, dob, email, phone, role, status);
                list.add(user);
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            super.close(conn, ps, rs);

        }
        return list;

    }
}
