/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.User;

/**
 *
 * @author My Laptop
 */
public interface UserDAO extends BasicDAO<User> {
    User checkLogin(String username, String password) throws Exception;
    User checkEmail(String email) throws Exception;
    User checkKey(String key) throws Exception;
}
