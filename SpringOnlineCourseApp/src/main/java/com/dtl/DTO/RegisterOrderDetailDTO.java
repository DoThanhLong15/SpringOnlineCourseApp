/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.Cart;
import com.dtl.pojo.RegisterDetail;
import com.dtl.pojo.RegisterOrder;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author LONG
 */
public class RegisterOrderDetailDTO extends RegisterOrderListDTO {

    private List<CartDTO> courseEnrollList;

    public RegisterOrderDetailDTO() {
        super();
    }
    
    public RegisterOrderDetailDTO(RegisterOrder registerOrder) {
        super(registerOrder);
        this.courseEnrollList = registerOrder.getRegisterDetailCollection().stream()
                .map(order -> new CartDTO(order.getId(), registerOrder.getLearnerId().getId(), order.getCourseId()))
                .collect(Collectors.toList());
    }

    /**
     * @return the cartList
     */
    public List<CartDTO> getCartList() {
        return courseEnrollList;
    }

    /**
     * @param courseEnrollList the cartList to set
     */
    public void setCartList(List<CartDTO> courseEnrollList) {
        this.courseEnrollList = courseEnrollList;
    }
}
