/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services;

import com.dtl.pojo.Cart;
import java.util.List;

/**
 *
 * @author LONG
 */
public interface CartService {

    void saveCart(Cart cart);

    void deleteCart(int id);

    void deleteCart(Cart cart);

    List<Cart> getCates(int userId, int courseId);

    Cart getCartById(int id);
}
