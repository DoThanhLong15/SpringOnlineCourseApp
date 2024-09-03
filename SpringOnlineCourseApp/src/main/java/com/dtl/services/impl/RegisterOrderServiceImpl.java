/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtl.services.impl;

import com.dtl.pojo.RegisterOrder;
import com.dtl.repository.RegisterDetailRepository;
import com.dtl.repository.RegisterOrderRepository;
import com.dtl.services.RegisterOrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LONG
 */
@Service
public class RegisterOrderServiceImpl implements RegisterOrderService{
    
    @Autowired
    private RegisterOrderRepository registerOrderRepo;
    @Autowired
    private RegisterDetailRepository registerDetailRepo;

    @Override
    public List<RegisterOrder> getRegisterOrderList(int learnerId) {
        return this.registerOrderRepo.getRegisterOrderList(learnerId);
    }

    @Override
    public RegisterOrder getRegisterOrderById(int orderId) {
        RegisterOrder order = this.registerOrderRepo.getRegisterOrderById(orderId);
        order.setRegisterDetailCollection(this.registerDetailRepo.getRegisterDetail(orderId));
        
        return order;
    }

    @Override
    public void saveRegisterOrder(RegisterOrder order) {
        this.registerOrderRepo.saveRegisterOrder(order);
    }

    @Override
    public void deleteRegisterOrder(RegisterOrder order) {
        this.registerOrderRepo.deleteRegisterOrder(order);
    }

    @Override
    public boolean isOrderOwner(RegisterOrder order, int userId) {
        return order.getLearnerId().getId() == userId;
    }
    
}
