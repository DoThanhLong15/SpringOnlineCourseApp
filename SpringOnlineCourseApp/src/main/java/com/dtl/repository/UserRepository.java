/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.User;

/**
 *
 * @author LONG
 */
public interface UserRepository {
    User getUserByUsername(String username);
    void addUser(User user);
}
