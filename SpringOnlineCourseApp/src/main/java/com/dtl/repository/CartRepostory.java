/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.repository;

import com.dtl.pojo.Cart;
import java.util.List;

/**
 *
 * @author LONG
 */
public interface CartRepostory {
    void saveCart(Cart cart);
    
    void deleteCart(int id);
    
    List<Cart> getCates(int userId);
    
    Cart getCartById(int id);
}
