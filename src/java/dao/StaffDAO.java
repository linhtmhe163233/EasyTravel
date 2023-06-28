/*
 * ISP392-IS1701-Group6
 * EasyTravel
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 14-06-2023      1.0                 DucTM           First Implement
 */
package dao;

import entity.Staff;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author DucTM
 */
public interface StaffDAO extends BasicDAO<Staff> {
    int getTotalItems(int agentId) throws Exception;
    List<Staff> getPageByAgent(int agentId, Pagination page) throws Exception;
    boolean isPhoneUnique(String phone) throws Exception;
}
