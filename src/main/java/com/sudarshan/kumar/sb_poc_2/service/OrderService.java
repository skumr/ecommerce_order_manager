package com.sudarshan.kumar.sb_poc_2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.models.Order;
import com.sudarshan.kumar.sb_poc_2.repositories.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository; 

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        List<Order> orders = orderRepository.findByCustomer(customer);

        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order", customer);
        }
        return orders;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order currOrder = getOrderById(id);

        currOrder.setCustomer(updatedOrder.getCustomer());
        currOrder.setShipment(updatedOrder.getShipment());

        return orderRepository.save(currOrder);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
    
}
