/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.DTO;

import com.dtl.pojo.RegisterOrder;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author LONG
 */
public class RegisterOrderDetailDTO extends RegisterOrderListDTO {

    private List<OrderDetailListDTO> courseEnrollList;

    public RegisterOrderDetailDTO() {
        super();
    }
    
    public RegisterOrderDetailDTO(RegisterOrder registerOrder) {
        super(registerOrder);
        this.courseEnrollList = registerOrder.getRegisterDetailCollection().stream()
                .map(order -> new OrderDetailListDTO(order.getId(), registerOrder.getLearnerId().getId(), order.getCourseId()))
                .collect(Collectors.toList());
    }

    /**
     * @return the cartList
     */
    public List<OrderDetailListDTO> getCartList() {
        return courseEnrollList;
    }

    /**
     * @param courseEnrollList the cartList to set
     */
    public void setCartList(List<OrderDetailListDTO> courseEnrollList) {
        this.courseEnrollList = courseEnrollList;
    }
}
