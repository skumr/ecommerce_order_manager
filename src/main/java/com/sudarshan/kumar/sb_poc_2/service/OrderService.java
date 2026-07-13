package com.sudarshan.kumar.sb_poc_2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sudarshan.kumar.sb_poc_2.repositories.OrderRepository;
import com.sudarshan.kumar.sb_poc_2.models.Order;

@Service
public class OrderService {

    private final OrderRepository orderRepository; 

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
}
