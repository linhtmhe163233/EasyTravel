/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 31-05-2023      1.0                 DucTM           First Implement
 * 06-06-2023      1.0                 DucTM           Fix database connection
 */
package dao.impl;

import dao.BasicDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Staff;

/*
 * This class contains methods for performing CRUD actions to table Staff in the database
 * 
 * @author DucTM
 */
public class StaffDAOImpl extends DBContext implements BasicDAO<Staff> {

    public StaffDAOImpl() throws Exception {
    }

    @Override
    public List<Staff> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Staff> get(int agentid) throws Exception {
        Connection conn = super.getConnection();
        List<Staff> list = new ArrayList<>();
        String query = "select * from staff where agent_id=?";

        int ID;
        String name;
        Date DOB;
        String phone;
        boolean gender;

        Staff staff;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, agentid);
            rs = ps.executeQuery();
            while (rs.next()) {
                ID = rs.getInt("id");
                name = rs.getString("name");
                DOB = rs.getDate("DOB");
                phone = rs.getString("phone");
                gender = rs.getBoolean("gender");

                staff = new Staff(ID, name, DOB, phone, gender, agentid);

                list.add(staff);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public void save(Staff t) throws Exception{
        Connection conn = super.getConnection();
        String query = "insert into staff(name, DOB, phone, gender, agent_id)"
                + "values(?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
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
            super.close(conn, ps, null);
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

}
