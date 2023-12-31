/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 27-05-2023      1.0                 DucTM           First Implement
 * 06-06-2023      1.0                 DucTM           Fix database connection
 * 13-06-2023      1.0                 DucTM           Add getTotalItems() and getPage()
 * 18-06-2023      1.0                 DucTM           Implement delete() (soft delete only) and enable()
 * 26-06-2023      1.0                 DucTM           Implements checkAllBookingDone() and closeOutdated()
 */
package dao.impl;

import dao.TourDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Tour;
import org.eclipse.jdt.internal.compiler.ast.SuperReference;
import utils.Pagination;

/*
 * This class contains methods for performing CRUD actions to table Tours in the database
 * 
 * @author DucTM
 */
public class TourDAOImpl extends DBContext implements TourDAO {

    public TourDAOImpl() throws Exception {
    }

    @Override
    public int getTotalItems(String search) throws Exception {
        String query = "select count(*) from tours where name like ? or type like ? or destination like ?";

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

    @Override
    public List<Tour> getPage(String search, Pagination page) throws Exception {
        closeOutdated();

        List<Tour> list = new ArrayList<>();
        String query = "select * from tours where name like ? or type like ? or destination like ? "
                + "order by id desc offset ? rows fetch next ? rows only";

        int id = 0;
        String name = null;
        boolean isEnabled = false;
        String destination = null;
        Date availableTo = null;
        float price = 0f;
        String description = null;
        int agentID = 0;
        String image = null;

        Tour tour = null;

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
                name = rs.getString("name");
                isEnabled = rs.getBoolean("is_enabled");
                destination = rs.getString("destination");
                availableTo = rs.getDate("available_to");
                price = rs.getFloat("price");
                description = rs.getString("description");
                agentID = rs.getInt("agent_id");
                image = rs.getString("image");

                tour = new Tour(id, name, isEnabled, destination, availableTo, price, description, agentID, image);
                list.add(tour);
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
    public List<Tour> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    @Override
    public List<Tour> get(int id) throws Exception {
        List<Tour> list = new ArrayList<>();
        String query = "select tours.id, name, type, is_enabled, destination, trip_length, available_from, available_to, "
                + "max_quantity, price, description, tours.agent_id, image, full_name, bank, code, qr "
                + "from tours join payment on tours.payment_id = payment.id "
                + "join users on tours.agent_id=users.id where tours.id=?";
        String name = null;
        String type = null;
        boolean isEnabled = false;
        String destination = null;
        int tripLength = 0;
        Date availableFrom = null;
        Date availableTo = null;
        int maxQuantity = 0;
        float price = 0f;
        String description = null;
        int agentID = 0;
        String image = null;
        String agentName = null;
        String bank = null;
        String code = null;
        String qr = null;

        Tour tour = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            rs.next();

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
            agentName = rs.getString("full_name");
            bank = rs.getString("bank");
            code = rs.getString("code");
            qr = rs.getString("qr");
            tour = new Tour(id, name, type, isEnabled, destination, tripLength, availableFrom,
                    availableTo, maxQuantity, price, description, agentID, image, agentName, bank, code, qr);
            list.add(tour);
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
    public void save(Tour t) throws Exception {
        String query = "insert into tours(name, type, is_enabled, destination, trip_length, available_from, available_to,"
                + "max_quantity, price, description, agent_id, image, payment_id)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
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
            ps.setInt(11, t.getAgentId());
            ps.setString(12, t.getImage());
            ps.setInt(13, t.getPaymentId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception("Unable to save data to database");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public void update(Tour t) throws Exception {
        if (!checkAllBookingDone(t.getId())) {
            save(t);
            return;
        }
        String query = "update tours set name=?, type=?, is_enabled=?, destination=?, trip_length=?, "
                + "available_from=?, available_to=?, max_quantity=?, price=?, description=?, agent_id=?, image=? "
                + "where id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
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
            ps.setInt(11, t.getAgentId());
            ps.setString(12, t.getImage());
            ps.setInt(13, t.getId());

            ps.executeUpdate();
        } catch (Exception ex) {
            throw new Exception("Unable to update data to database");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public void delete(Tour t) throws Exception {
        String query = "update tours set is_enabled=0 where id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, t.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Unable to disable this tour in database");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public List<Tour> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void enable(int id) throws Exception {
        String query = "update tours set is_enabled=1 where id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            query = "update tours set available_from=GETDATE() where id=? and available_from<GETDATE()";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            query = "update tours set available_to=DATEADD(day, 1, GETDATE()) where id=? and available_to<GETDATE()";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Unable to enable this tour in database");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public boolean checkAllBookingDone(int tourId) throws Exception {
        String sql = "select count(*) from booking where tour_id=? and status in ('Unpaid', 'Paid', 'Ready')";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tourId);

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) == 0;
        } catch (Exception e) {
            throw new Exception("Unable to get data from database");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public void closeOutdated() throws Exception {
        String sql = "update tours set is_enabled=0 where available_to < getdate()";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Database fails");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }
}
