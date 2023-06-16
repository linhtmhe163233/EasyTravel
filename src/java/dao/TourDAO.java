/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 13-06-2023      1.0                 DucTM           First Implement
 */
package dao;

import entity.Tour;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author DucTM
 */
public interface TourDAO extends BasicDAO<Tour> {
    List<Tour> getPage(Pagination page) throws Exception;
    int getTotalItems() throws Exception;
}
