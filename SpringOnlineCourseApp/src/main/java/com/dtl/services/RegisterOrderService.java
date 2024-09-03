/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtl.services;

import com.dtl.pojo.RegisterOrder;
import java.util.List;

/**
 *
 * @author LONG
 */
public interface RegisterOrderService {

    List<RegisterOrder> getRegisterOrderList(int learnerId);

    RegisterOrder getRegisterOrderById(int orderId);

    void saveRegisterOrder(RegisterOrder order);

    void deleteRegisterOrder(RegisterOrder order);
    
    boolean isOrderOwner(RegisterOrder order, int userId);
}
