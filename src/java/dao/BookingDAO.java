/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 27-06-2023      1.0                 DucTM           First Implement
 */
package dao;

import entity.Booking;
import entity.Facility;
import java.sql.Date;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author DucTM
 */
public interface BookingDAO extends BasicDAO<Booking> {

    List<Booking> getTourHistory(int touristId, Pagination page) throws Exception;

    List<Booking> getBookingList(int agentId, Pagination page) throws Exception;

    int getTotalItems(int searchBy, String type) throws Exception;

    boolean checkSchedule(int touristId, Date from, Date to) throws Exception;

    public void addFacilities(Facility facility) throws Exception;
    
    public Facility getFacilities(int bookingId) throws Exception;
    
    public void finishTours() throws Exception;
}
