/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.controllers;

import com.dtl.DTO.RegisterOrderDetailDTO;
import com.dtl.DTO.RegisterOrderListDTO;
import com.dtl.components.ErrorResponseUtil;
import com.dtl.pojo.Cart;
import com.dtl.pojo.Course;
import com.dtl.pojo.CourseProgress;
import com.dtl.pojo.RegisterDetail;
import com.dtl.pojo.RegisterOrder;
import com.dtl.pojo.User;
import com.dtl.services.CartService;
import com.dtl.services.CourseProgressService;
import com.dtl.services.CourseService;
import com.dtl.services.RegisterOrderService;
import com.dtl.services.UserService;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LONG
 */
@RestController
@RequestMapping("/api/users/register-orders")
public class ApiRegisterOrder {

    @Autowired
    private UserService userService;
    @Autowired
    private RegisterOrderService registerOrderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CourseProgressService courseProgressService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ErrorResponseUtil errorResponseUtil;

    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> getOrderList(Locale locale, Principal user) {
        Map<String, Object> response = new HashMap<>();

        User userDetail = this.userService.getUserByUsername(user.getName());
        List<RegisterOrderListDTO> orderList = this.registerOrderService.getRegisterOrderList(userDetail.getId())
                .stream()
                .map(order -> new RegisterOrderListDTO(order))
                .collect(Collectors.toList());

        response.put("data", orderList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    @CrossOrigin
    public ResponseEntity<Object> getOrderDetail(Locale locale, Principal user, @PathVariable(value = "orderId") int orderId) {
        Map<String, Object> response = new HashMap<>();

        User userDetail = this.userService.getUserByUsername(user.getName());
        RegisterOrder registerOrder = this.registerOrderService.getRegisterOrderById(orderId);
        
        if (!this.registerOrderService.isOrderOwner(registerOrder, userDetail.getId())) {
            return this.errorResponseUtil.buildErrorResponse("user.permission.deny", locale);
        }

        response.put("data", new RegisterOrderDetailDTO(registerOrder));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Object> registerCourse(Locale locale, Principal user) {

        User userDetail = this.userService.getUserByUsername(user.getName());
        List<Cart> cartList = this.cartService.getCates(userDetail.getId(), -1);

        if (cartList == null || cartList.isEmpty()) {
            return this.errorResponseUtil.buildErrorResponse("cart.notFound.errMsg", locale);
        }

        RegisterOrder order = new RegisterOrder();
        order.setLearnerId(userDetail);

        List<RegisterDetail> registerDetails = cartList.stream().map(cart -> {
            RegisterDetail orderDetail = new RegisterDetail();
            orderDetail.setCourseId(cart.getCourseId());
            orderDetail.setPrice(cart.getCourseId().getPrice());
            orderDetail.setRegisterOrderId(order);

            // Create and save CourseProgress
            CourseProgress courseProgress = new CourseProgress();
            courseProgress.setCourseId(cart.getCourseId());
            courseProgress.setLearnerId(userDetail);
            courseProgress.setLessonCompleteCount(0);
            this.courseProgressService.saveCourseProgress(courseProgress);
            
            Course course = this.courseService.getCourseById(cart.getCourseId().getId());
            course.setParticipantCount(course.getParticipantCount() + 1);
            this.courseService.addOrUpdateCourse(course);

            // Remove cart item
            this.cartService.deleteCart(cart);

            return orderDetail;
        }).collect(Collectors.toList());

        order.setRegisterDetailCollection(registerDetails);

        // Save the order along with its details
        this.registerOrderService.saveRegisterOrder(order);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
