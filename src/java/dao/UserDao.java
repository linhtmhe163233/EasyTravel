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

//        int id;
////        username = null;
////        password = null;
////        String fullname;
////        Date dob;
////        String email;
////        String phone;
////        String role;
////        String status;
//
////        User user;
////        PreparedStatement ps ;
////        ResultSet rs ;
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
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean insertUser(String newUsername, String newEmail, String newPassword, String newFullname, String newPhone, String newStatus, String newRole, Date newDob) {

        PreparedStatement ps = null;
        String sql = "INSERT INTO users(username,email, password, fullname, phone, status, role,dob) VALUES (?,?, ?, ?, ?, ?, ?, ?)";

        try {

            ps = conn.prepareStatement(sql);
            ps.setString(1, newUsername);
            ps.setString(2, newEmail);
            ps.setString(3, newPassword);
            ps.setString(4, newFullname);
            ps.setString(5, newPhone);
            ps.setString(6, newStatus);
            ps.setString(7, newRole);
            ps.setDate(8, newDob);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

                user = new User(id, username, password, fullname, email, phone, role, dob, status);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

                user = new User(id, username, password, fullname, email, phone, role, dob, status);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.close(conn, ps, rs);

        }
        return list;
    }
}