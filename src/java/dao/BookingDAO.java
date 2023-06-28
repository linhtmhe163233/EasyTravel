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
import java.util.List;
import utils.Pagination;

/**
 *
 * @author DucTM
 */
public interface BookingDAO extends BasicDAO<Booking> {
    List<Booking> getTourHistory(int touristId, Pagination page) throws Exception;
    int getTotalHistoryItems(int touristId) throws Exception;
}