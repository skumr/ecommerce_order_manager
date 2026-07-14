package com.sudarshan.kumar.sb_poc_2.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.models.Order;
import com.sudarshan.kumar.sb_poc_2.service.OrderService;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/allorders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @GetMapping("/{id}")
    public Order getOrderById(@RequestParam Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/search")
    public List<Order> getOrdersByCustomer(Customer customer) {
        return orderService.getOrdersByCustomer(customer);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    


    

    
    
}
