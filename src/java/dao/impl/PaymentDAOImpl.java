/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 16-07-2023      1.0                 DucTM           First Implement
 */
package dao.impl;

import dao.BasicDAO;
import entity.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl extends DBContext implements BasicDAO<Payment> {

    @Override
    public List<Payment> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Payment> get(int agentId) throws Exception {
        String query = "select * from payment where agent_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Payment> list = new ArrayList<>();
        Payment payment = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, agentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Payment(rs.getInt("agent_id"),
                        rs.getString("bank"),
                        rs.getString("code"),
                        rs.getString("qr")));
            }
        } catch (Exception e) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(Payment t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Payment t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Payment t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Payment> search(String keyword) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
