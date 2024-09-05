/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.CartDTO;
import com.dtl.components.ErrorResponseUtil;
import com.dtl.pojo.Cart;
import com.dtl.pojo.CourseProgress;
import com.dtl.pojo.User;
import com.dtl.services.CartService;
import com.dtl.services.CourseProgressService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LONG
 */
@RestController
@RequestMapping("/api/users/carts")
public class ApiCartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseProgressService courseProgressService;
    @Autowired
    private ErrorResponseUtil errorResponseUtil;

    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> getCartList(Locale locale, Principal user) {
        Map<String, Object> response = new HashMap<>();

        User userDetail = this.userService.getUserByUsername(user.getName());

        List<CartDTO> cartList = this.cartService.getCates(userDetail.getId(), -1)
                .stream()
                .map(cart -> new CartDTO(cart.getId(), cart.getLearnerId().getId(), cart.getCourseId()))
                .collect(Collectors.toList());

        response.put("data", cartList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> addToCart(@Valid @RequestBody Cart cart,
            BindingResult bindingResult, Principal user, Locale locale) {
        cart.setLearnerId(this.userService.getUserByUsername(user.getName()));

        List<Cart> cartList = this.cartService.getCates(cart.getLearnerId().getId(), cart.getCourseId().getId());
        if (!cartList.isEmpty()) {
            return this.errorResponseUtil.buildErrorResponse("cart.courseId.exist.errMsg", locale);
        }

        CourseProgress courseProgress
                = this.courseProgressService.getCourseProgress(cart.getLearnerId().getId(), cart.getCourseId().getId());
        if (courseProgress != null) {
            return this.errorResponseUtil.buildErrorResponse("course.hasEnroll.errMsg", locale);
        }

        this.cartService.saveCart(cart);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}")
    @CrossOrigin
    public ResponseEntity<Object> deleteCart(@PathVariable(value = "cartId") int cartId, Principal user, Locale locale) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        Cart cart = this.cartService.getCartById(cartId);

        if (!this.cartService.isCartOwner(cart.getLearnerId(), userDetail)) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        this.cartService.deleteCart(cart);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
