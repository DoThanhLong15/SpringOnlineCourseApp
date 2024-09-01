/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.UserRole;
import com.dtl.repository.UserRoleRepository;
import com.dtl.services.UserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class UserRoleServiceImpl implements UserRoleService{
    
    @Autowired
    private UserRoleRepository userRoleRepo;

    @Override
    public List<UserRole> getUserRoles() {
        return this.userRoleRepo.getUserRoles();
    }

    @Override
    public UserRole getUserRoleById(int id) {
        return this.userRoleRepo.getUserRoleById(id);
    }
    
}
