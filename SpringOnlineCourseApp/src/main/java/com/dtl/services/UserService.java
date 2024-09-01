/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.services;

import com.dtl.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author LONG
 */
public interface UserService extends UserDetailsService {

    User getUserByUsername(String username);

    List<User> getUsers(Map<String, String> params);

    void addOrUpdateUser(User user);

    User getUserById(int id);
    
    boolean authUser(String username, String password);
}
