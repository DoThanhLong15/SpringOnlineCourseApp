/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.UserRole;
import java.util.List;

/**
 *
 * @author LONG
 */
public interface UserRoleRepository {
    UserRole getUserRoleById(int id);
    List<UserRole> getUserRoles();
}
