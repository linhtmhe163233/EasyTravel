/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 31-05-2023      1.0                 DucTM           First Implement
 * 06-06-2023      1.0                 DucTM           Fix database connection
 * 14-06-2023      1.0                 DucTM           Implement StaffDAO
 */
package dao.impl;

import dao.StaffDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Staff;
import utils.Pagination;

/*
 * This class contains methods for performing CRUD actions to table Staff in the database
 * 
 * @author DucTM
 */
public class StaffDAOImpl extends DBContext implements StaffDAO {

    public StaffDAOImpl() throws Exception {
    }

    @Override
    public List<Staff> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Staff> get(int id) throws Exception {
        List<Staff> list = new ArrayList<>();
        String query = "select * from staff where id=?";

        int ID;
        String name;
        Date DOB;
        String phone;
        boolean gender;
        int agentId;

        Staff staff;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            while (rs.next()) {
                ID = rs.getInt("id");
                name = rs.getString("name");
                DOB = rs.getDate("DOB");
                phone = rs.getString("phone");
                gender = rs.getBoolean("gender");
                agentId = rs.getInt("agent_id");
                staff = new Staff(ID, name, DOB, phone, gender, agentId);

                list.add(staff);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(Staff t) throws Exception {
        String query = "insert into staff(name, DOB, phone, gender, agent_id)"
                + "values(?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = super.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, t.getName());
            ps.setDate(2, t.getDOB());
            ps.setString(3, t.getPhone());
            ps.setBoolean(4, t.isGender());
            ps.setInt(5, t.getAgentID());

            ps.execute();
        } catch (SQLException ex) {
            throw new Exception("Unable to save data to database");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public void update(Staff t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Staff t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Staff> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getTotalItems(int agentId) throws Exception {
        String query = "select count(*) from staff where agent_id=?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, agentId);
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

    @Override
    public List<Staff> getPageByAgent(int agentId, Pagination page) throws Exception {
        List<Staff> list = new ArrayList<>();
        String query = "select * from staff where agent_id=? order by id offset ? rows fetch next ? rows only";

        int ID;
        String name;
        Date DOB;
        String phone;
        boolean gender;

        Staff staff;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, agentId);
            ps.setInt(2, page.getOffset());
            ps.setInt(3, page.getItemsPerPage());

            rs = ps.executeQuery();
            while (rs.next()) {
                ID = rs.getInt("id");
                name = rs.getString("name");
                DOB = rs.getDate("DOB");
                phone = rs.getString("phone");
                gender = rs.getBoolean("gender");
                agentId = rs.getInt("agent_id");
                staff = new Staff(ID, name, DOB, phone, gender, agentId);
                list.add(staff);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public boolean isPhoneUnique(String phone) throws Exception {
        String query = "select phone from staff where phone=?";

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

    @Override
    public List<Staff> getAllByAgent(int agentId) throws Exception {
        List<Staff> list = new ArrayList<>();
        String query = "select * from staff where agent_id=?";

        int ID;
        String name;
        Date DOB;
        String phone;
        boolean gender;

        Staff staff;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, agentId);

            rs = ps.executeQuery();
            while (rs.next()) {
                ID = rs.getInt("id");
                name = rs.getString("name");
                DOB = rs.getDate("DOB");
                phone = rs.getString("phone");
                gender = rs.getBoolean("gender");
                agentId = rs.getInt("agent_id");
                staff = new Staff(ID, name, DOB, phone, gender, agentId);
                list.add(staff);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }
}
