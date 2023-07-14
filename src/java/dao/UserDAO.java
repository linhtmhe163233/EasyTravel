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
    User checkEmail(String email) throws Exception;
    User checkKey(String key) throws Exception;
    int getTotalItems() throws Exception;
    List<User> getPage(Pagination page) throws Exception;
    boolean isPhoneUnique(String phone, int id) throws Exception;
    boolean isEmailUnique(String email, int id) throws Exception;
    List<User> getPage(String search, Pagination page) throws Exception;
}
