/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.Cart;
import com.dtl.services.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dtl.repository.CartRepository;

/**
 *
 * @author LONG
 */
@Service
public class CartServiceImpl implements CartService{
    
    @Autowired
    private CartRepository cartRepo;

    @Override
    public void saveCart(Cart cart) {
        this.cartRepo.saveCart(cart);
    }

    @Override
    public void deleteCart(int id) {
        this.cartRepo.deleteCart(id);
    }

    @Override
    public List<Cart> getCates(int userId, int courseId) {
        return this.cartRepo.getCates(userId, courseId);
    }

    @Override
    public Cart getCartById(int id) {
        return this.cartRepo.getCartById(id);
    }

    @Override
    public void deleteCart(Cart cart) {
        this.cartRepo.deleteCart(cart);
    }
            
    
}
