package dao.impl;

import dao.BookingDAO;
import entity.Booking;
import entity.Facility;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.Pagination;

public class BookingDAOImpl extends DBContext implements BookingDAO {

    public BookingDAOImpl() throws Exception {
    }

    @Override
    public List<Booking> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Booking> get(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Booking t) throws Exception {
        String sql = "INSERT INTO booking(tourist_id, tour_id, start_date, "
                + "tourists_quantity, status, note, book_time, payment)"
                + "VALUES(?,?,?,?,?,?,?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, t.getTouristId());
            ps.setInt(2, t.getTourId());
            ps.setDate(3, t.getStartDate());
            ps.setInt(4, t.getTouristsQuantity());
            ps.setString(5, t.getStatus());
            ps.setString(6, t.getNote());
            ps.setTimestamp(7, t.getBookTime());
            ps.setString(8, t.getPayment());

            ps.execute();
        } catch (Exception e) {
            throw new Exception("Unable to book this tour now, try again later!");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public void update(Booking t) throws Exception {
        String query = "update booking set status=?, reason=? where id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, t.getStatus());
            ps.setString(2, t.getReason());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Can't process this request now, try again later!");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public void delete(Booking t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Booking> search(String keyword) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Booking> getTourHistory(int touristId, Pagination page) throws Exception {
        String sql = "select booking.id, tour_id, book_time, start_date, tourists_quantity, booking.status, note, name, reason, payment, bank, code, qr  "
                + "from booking join tours "
                + "on tour_id=tours.id "
                + "join payment on payment.id=tours.payment_id "
                + "where tourist_id=? "
                + "order by book_time desc "
                + "offset ? rows fetch next ? rows only";
        List<Booking> list = new ArrayList();
        int id;
        int tourId;
        Timestamp bookTime;
        Date startDate;
        int touristsQuantity;
        String status;
        String note;
        String tourName;
        String reason;
        String payment;
        String bank;
        String code;
        String qr;
        Booking booking = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, touristId);
            ps.setInt(2, page.getOffset());
            ps.setInt(3, page.getItemsPerPage());
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                tourId = rs.getInt("tour_id");
                bookTime = rs.getTimestamp("book_time");
                startDate = rs.getDate("start_date");
                touristsQuantity = rs.getInt("tourists_quantity");
                status = rs.getString("status");
                note = rs.getString("note");
                tourName = rs.getString("name");
                reason = rs.getString("reason");
                payment = rs.getString("payment");
                bank = rs.getString("bank");
                code = rs.getString("code");
                qr = rs.getString("qr");
                booking = new Booking(id, touristId, tourId, bookTime, startDate,
                        touristsQuantity, status, note, tourName, reason, payment, bank, code, qr);
                list.add(booking);
            }
        } catch (Exception e) {
            throw new Exception("Unable to get data from database!");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public List<Booking> getBookingList(int agentId, Pagination page) throws Exception {
        String sql = "select booking.id, tour_id, book_time, start_date, tourists_quantity, booking.status, "
                + "note, name, tourist_id, full_name, phone, email, trip_length, reason, payment "
                + "from booking "
                + "join users on tourist_id=users.id "
                + "join tours on tour_id=tours.id "
                + "where agent_id=? "
                + "order by book_time desc "
                + "offset ? rows fetch next ? rows only";
        List<Booking> list = new ArrayList();
        int id;
        int touristId;
        int tourId;
        Timestamp bookTime;
        Date startDate;
        int touristsQuantity;
        String status;
        String note;
        String tourName;
        String touristName;
        String touristPhone;
        String touristEmail;
        int tourLength;
        String reason;
        String payment;
        Booking booking = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, agentId);
            ps.setInt(2, page.getOffset());
            ps.setInt(3, page.getItemsPerPage());
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
                touristId = rs.getInt("tourist_id");
                tourId = rs.getInt("tour_id");
                bookTime = rs.getTimestamp("book_time");
                startDate = rs.getDate("start_date");
                touristsQuantity = rs.getInt("tourists_quantity");
                status = rs.getString("status");
                note = rs.getString("note");
                tourName = rs.getString("name");
                touristName = rs.getString("full_name");
                touristPhone = rs.getString("phone");
                touristEmail = rs.getString("email");
                tourLength = rs.getInt("trip_length");
                reason = rs.getString("reason");
                payment = rs.getString("payment");
                booking = new Booking(id, touristId, tourId, bookTime, startDate, touristsQuantity, status, note,
                        touristName, tourName, touristPhone, touristEmail, tourLength, reason, payment);
                list.add(booking);
            }
        } catch (Exception e) {
            throw new Exception("Unable to get data from database!");
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public int getTotalItems(int searchBy, String type) throws Exception {
        String query = null;
        if (type.equals("history")) {
            query = "select count(*) from booking where tourist_id=?";
        } else if (type.equals("request")) {
            query = "select count(*) from booking join tours on tour_id=tours.id "
                    + "where tours.agent_id in (select id from users where id=?)";
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, searchBy);
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
    public boolean checkSchedule(int touristId, Date from, Date to) throws Exception {
        String query = "select count(*) from booking join tours on booking.tour_id=tours.id "
                + "where tourist_id=? and status in ('Unpaid', 'Paid', 'Ready') and "
                + "((start_date>=? and start_date<=?) or"
                + "(dateadd(day, trip_length, start_date)>=? and dateadd(day, trip_length, start_date)<=?))";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, touristId);
            ps.setDate(2, from);
            ps.setDate(3, to);
            ps.setDate(4, from);
            ps.setDate(5, to);
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
    public void addFacilities(Facility facility) throws Exception {
        String query = "insert into bookingDetails(booking_id, vehicle_id, hotel_id, staff_id, restaurant_id) "
                + "values(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, facility.getBookingId());
            ps.setInt(2, facility.getVehicleId());
            ps.setInt(3, facility.getHotelId());
            ps.setInt(4, facility.getStaffId());
            ps.setInt(5, facility.getRestaurantId());
            ps.execute();
        } catch (Exception e) {
            throw new Exception("Unable to save data to database");
        } finally {
            closePs(ps);
            closeConnection(conn);
        }
    }

    @Override
    public Facility getFacilities(int bookingId) throws Exception {
        String query = "select booking_id, vehicles.type as vType, driver_phone, max_passengers, "
                + "staff.name as sName, staff.phone as sPhone, "
                + "hotels.name as hName, hotels.phone as hPhone, stars, "
                + "restaurants.type as rType, restaurants.phone as rPhone "
                + "from bookingDetails "
                + "join vehicles on bookingDetails.vehicle_id=vehicles.id "
                + "join staff on bookingDetails.staff_id=staff.id "
                + "join hotels on bookingDetails.hotel_id=hotels.id "
                + "join restaurants on bookingDetails.restaurant_id=restaurants.id "
                + "where booking_id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String vehicleInfo = "";
        String staffInfo = "";
        String hotelInfo = "";
        String restaurantInfo = "";
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();
            while (rs.next()) {
                vehicleInfo += rs.getString("vType") + " - " + rs.getString("driver_phone") + " - " + rs.getInt("max_passengers") + " seats";
                staffInfo += rs.getString("sName") + " - " + rs.getString("sPhone");
                hotelInfo += rs.getString("hName") + " - " + rs.getString("hPhone") + " - " + rs.getInt("stars");
                restaurantInfo += rs.getString("rType") + " - " + rs.getString("rPhone");
                return new Facility(bookingId, vehicleInfo, staffInfo, hotelInfo, restaurantInfo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeRs(rs);
            closePs(ps);
            closeConnection(conn);
        }
        return null;
    }
}
