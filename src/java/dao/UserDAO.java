/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.User;
import java.util.List;
import utils.Pagination;

/**
 *
 * @author My Laptop
 */
public interface UserDAO extends BasicDAO<User> {

    User checkLogin(String username, String password) throws Exception;

    boolean checkUserBanned(String username) throws Exception;

    User checkEmail(String email) throws Exception;

    User checkKey(String key) throws Exception;

//    int getTotalItems() throws Exception;
    int getTotalItems(String search) throws Exception;

//    List<User> getPage(Pagination page) throws Exception;
    boolean isPhoneUnique(String phone, int id) throws Exception;

    boolean isEmailUnique(String email, int id) throws Exception;

    boolean registerPhoneUnique(String phone) throws Exception;

    boolean registerEmailUnique(String email) throws Exception;

    boolean registerUsernameUnique(String username) throws Exception;

    List<User> getPage(String search, Pagination page) throws Exception;

    void updateStatus(String status, int id) throws Exception;
}
