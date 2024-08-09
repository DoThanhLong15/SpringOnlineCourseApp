/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.User;
import com.dtl.repository.UserRepository;
import com.dtl.repository.UserRoleRepository;
import com.dtl.services.UserService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LONG
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private UserRoleRepository userRoleRepo;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid Username!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRoleId().getRole()));

        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(this.passEncoder.encode("123456"));
//        user.setUserRoleId(this.userRoleRepo.getUserRoleById(1));
        
        this.userRepo.addUser(user);
        
        return user;
    }
}
