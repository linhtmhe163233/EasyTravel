/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 27-05-2023      1.0                 DucTM           First Implement
 * 06-06-2023      1.0                 DucTM           Fix database connection
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Tour;

/*
 * This class contains methods for performing CRUD actions to table Tours in the database
 * 
 * @author DucTM
 */
public class TourDAO extends DBContext implements DAO<Tour> {

    public TourDAO() throws Exception {
    }
    
    @Override
    public List<Tour> getAll() throws Exception {
        Connection conn = super.getConnection();
        List<Tour> list = new ArrayList<>();
        String query = "select * from tours";
        
        int ID;
        String name;
        String type;
        boolean isEnabled;
        String destination;
        int tripLength;
        Date availableFrom;
        Date availableTo;
        int maxQuantity;
        float price;
        String description;
        int agentID;
        String image;

        Tour tour;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ID = rs.getInt("id");
                name = rs.getString("name");
                type = rs.getString("type");
                isEnabled = rs.getBoolean("is_enabled");
                destination = rs.getString("destination");
                tripLength = rs.getInt("trip_length");
                availableFrom = rs.getDate("available_from");
                availableTo = rs.getDate("available_to");
                maxQuantity = rs.getInt("max_quantity");
                price = rs.getFloat("price");
                description = rs.getString("description");
                agentID = rs.getInt("agent_id");
                image = rs.getString("image");

                tour = new Tour(ID, name, type, isEnabled, destination, tripLength, availableFrom,
                        availableTo, maxQuantity, price, description, agentID, image);
                list.add(tour);
            }
        } catch (SQLException ex) {
            throw new Exception("Unable to get data from database");
        } finally {
            super.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<Tour> get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
//        String query = "select * from tours where id=?";
//        
//        Tour tour = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            ps = conn.prepareStatement(query);
//            ps.setInt(1, id);
//
//            rs = ps.executeQuery();
//            rs.next();
//
//            String name = rs.getString("name");
//            String type = rs.getString("type");
//            boolean isEnabled = rs.getBoolean("is_enabled");
//            String destination = rs.getString("destination");
//            int tripLength = rs.getInt("trip_length");
//            Date availableFrom = rs.getDate("available_from");
//            Date availableTo = rs.getDate("available_to");
//            int maxQuantity = rs.getInt("max_quantity");
//            float price = rs.getFloat("price");
//            String description = rs.getString("description");
//            int agentID = rs.getInt("agent_id");
//            String image=rs.getString("image");
//
//            tour = new Tour(id, name, type, isEnabled, destination, tripLength, availableFrom, 
//                    availableTo, maxQuantity, price, description, agentID, image);
//
//        } catch (SQLException ex) {
//            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            super.close(conn, ps, rs);
//        }
//        return tour;
    }

    @Override
    public void save(Tour t) throws Exception {
        Connection conn = super.getConnection();
        String query = "insert into tours(name, type, is_enabled, destination, trip_length, available_from, available_to,"
                + "max_quantity, price, description, agent_id, image)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getName());
            ps.setString(2, t.getType());
            ps.setBoolean(3, t.isEnabled());
            ps.setString(4, t.getDestination());
            ps.setInt(5, t.getTripLength());
            ps.setDate(6, t.getAvailableFrom());
            ps.setDate(7, t.getAvailableTo());
            ps.setInt(8, t.getMaxQuantity());
            ps.setFloat(9, t.getPrice());
            ps.setString(10, t.getDescription());
            ps.setInt(11, t.getAgentID());
            ps.setString(12, t.getImage());

            ps.execute();
        } catch (SQLException ex) {
            throw new Exception("Unable to save data to database");
        } finally {
            super.close(conn, ps, null);
        }
    }

    @Override
    public void update(Tour t) {
    }

    @Override
    public void delete(Tour t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Tour> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
