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
import dao.UserDAO;
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
import utils.Pagination;

/**
 *
 * @author LinhTM
 */
public class UserDaoImpl extends DBContext implements UserDAO {

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
    
//    public boolean checkUserBanned(String username) throws Exception {
//        String query = "select status from users where account_name=? and status ='Banned'";
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = getConnection();
//            ps = conn.prepareStatement(query);
//            ps.setString(1, username);
//        
//            rs = ps.executeQuery();
//
//            return !rs.next();
//        } catch (SQLException ex) {
//            throw new Exception("Unable to get data from database");
//        } finally {
//            closeRs(rs);
//            closePs(ps);
//            closeConnection(conn);
//        }
//    }
    
    

    public User checkEmail(String email) throws Exception {
        Connection conn = super.getConnection();
        String sql = "SELECT * FROM users WHERE email=?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);

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

    public User checkKey(String key) throws Exception {
        Connection conn = super.getConnection();
        String sql = "SELECT * FROM users WHERE [key]=?";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, key);

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
            throw new Exception("Unable to create new user" + ex.getMessage());
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void update(User t) throws Exception {
        Connection conn = super.getConnection();
        String query = "update users set account_name = ?, password = ?, full_name= ?, DOB = ?, email = ?, phone = ?, role =?, status = ?, [key] = ? where id=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getUsername());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getFullname());
            ps.setDate(4, t.getDob());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getPhone());
            ps.setString(7, t.getRole());
            ps.setString(8, t.getStatus());
            ps.setString(9, t.getKey());
            ps.setInt(10, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Unable to update data to database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void delete(User t) throws Exception {
        Connection conn = super.getConnection();

        String sql = "delete from users where id = ?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Unable to delete data to database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public List<User> search(String keyword) throws Exception {
        Connection conn = super.getConnection();
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE full_name like ? or [key]=? or email=?";

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
            ps.setString(3, keyword);

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

//    @Override
//    public int getTotalItems() throws Exception {
//        String query = "select count(*) from users";
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = getConnection();
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();
//            rs.next();
//            return rs.getInt(1);
//        } catch (Exception e) {
//            throw new Exception("Unable to get data from database");
//        } finally {
//            closeRs(rs);
//            closePs(ps);
//            closeConnection(conn);
//        }
//    }
    public int getTotalItems(String search) throws Exception {
        String query = "select count(*) from users where account_name like ? or phone like ? or email like ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

//    @Override
//    public List<User> getPage(Pagination page) throws Exception {
//        List<User> list = new ArrayList<>();
//        String query = "select * from users order by id offset ? rows fetch next ? rows only";
//
//        int id;
//        String fullname;
//        String username;
//        String email;
//
//        Date DOB;
//        String phone;
//        String role;
//        String status;
//
//        User user;
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = getConnection();
//            ps = conn.prepareStatement(query);
//
//            ps.setInt(1, page.getOffset());
//            ps.setInt(2, page.getItemsPerPage());
//
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                id = rs.getInt("id");
//                fullname = rs.getString("full_name");
//                username = rs.getString("account_name");
//                email = rs.getString("email");
//                DOB = rs.getDate("DOB");
//                phone = rs.getString("phone");
//                role = rs.getString("role");
//                status = rs.getString("status");
//
//                user = new User(id, username, fullname, DOB, email, phone, role, status);
//
//                list.add(user);
//            }
//        } catch (SQLException ex) {
//            throw new Exception("Unable to get data from database");
//        } finally {
//            closeRs(rs);
//            closePs(ps);
//            closeConnection(conn);
//        }
//        return list;
//    }
    public void toggleUserStatus(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        String activeUser = "update users set status ='Active' Where id = ?";
        String banUser = "update users set status = 'Banned' Where id = ?";
        String findUserStatusByUserID = "select status from users where id = ?";
        String status = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(findUserStatusByUserID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getString(activeUser);
            }

            if (status.equalsIgnoreCase("Active")) {
                ps = conn.prepareStatement(banUser);
            } else {
                ps = conn.prepareStatement(activeUser);
            }

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
//            throw new Exception("Unable to get data from database");
            throw e;
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    public boolean isPhoneUnique(String phone, int id) throws Exception {
        String query = "select phone from users where phone=? and id !=? ";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, phone);
            ps.setInt(2, id);

            rs = ps.executeQuery();

            return !rs.next();
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    public boolean registerPhoneUnique(String phone) throws Exception {
        String query = "select phone from users where phone=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, phone);

            rs = ps.executeQuery();

            return !rs.next();
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    public boolean registerEmailUnique(String email) throws Exception {
        String query = "select email from users where email=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();

            return !rs.next();
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    public boolean registerUsernameUnique(String username) throws Exception {
        String query = "select account_name from users where account_name=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            return !rs.next();
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    public boolean isEmailUnique(String email, int id) throws Exception {
        String query = "select email from users where email=? and id !=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, id);

            rs = ps.executeQuery();

            return !rs.next();
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    public List<User> getPage(String search, Pagination page) throws Exception {
//        closeOutdated();

        List<User> list = new ArrayList<>();
        String query = "select * from users where account_name like ? or phone like ? or email like ? "
                + "order by id offset ? rows fetch next ? rows only";

        int id = 0;
        String fullname = null;
        String username = null;
        Date DOB = null;
        String phone = null;
        String email = null;
        String role = null;
        String status = null;

        User user = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ps.setInt(4, page.getOffset());
            ps.setInt(5, page.getItemsPerPage());

            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                fullname = rs.getString("full_name");
                username = rs.getString("account_name");
                DOB = rs.getDate("DOB");
                phone = rs.getString("phone");
                email = rs.getString("email");
                role = rs.getString("role");
                status = rs.getString("status");

                user = new User(id, username, fullname, DOB, email, phone, role, status);
                list.add(user);

            }
        } catch (SQLException ex) {
//            throw new Exception("Unable to get data from database");
            throw ex;
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }

    public void updateStatus(String status,int id) throws Exception {
        Connection conn = super.getConnection();
        String query = "update users set status = ? where id=?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1,status);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Unable to update data to database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    public static void main(String[] args) {
        try {
            UserDaoImpl dao = new UserDaoImpl();
//            String email = null;
            String username = "tranlinhnhoi";
//            int index = 1;
//            int totalItems = dao.getTotalItems("");
//            Pagination page = new Pagination(totalItems, 2, index);
//            List<User> list = dao.getPage("", page);
//            System.out.println(list);
//               dao.toggleUserStatus(1);
//               System.out.println(dao.getAll());
            boolean u = dao.checkUserBanned(username);
            System.out.println(u);
//            boolean u = dao.registerUsernameUnique(email);
//            System.out.println(u);

//            dao.updateStatus("Banned",1);

   
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
