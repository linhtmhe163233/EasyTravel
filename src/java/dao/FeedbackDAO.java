/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.FeedbackThread;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author My Laptop
 */
public interface FeedbackDAO extends BasicDAO<FeedbackThread> {
     List<FeedbackThread> getPage(Pagination page,int tourID) throws Exception;
    int getTotalItems(int tourID) throws Exception;
}
