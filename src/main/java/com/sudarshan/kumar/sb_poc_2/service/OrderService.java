package com.sudarshan.kumar.sb_poc_2.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sudarshan.kumar.sb_poc_2.dto.order.CreateOrderRequestDto;
import com.sudarshan.kumar.sb_poc_2.dto.order.OrderResponseDto;
import com.sudarshan.kumar.sb_poc_2.exceptions.ResourceNotFoundException;
import com.sudarshan.kumar.sb_poc_2.mapper.OrderMapper;
import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.models.Order;
import com.sudarshan.kumar.sb_poc_2.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        List<Order> orders = orderRepository.findByCustomerOrderByCreatedAtDesc(customer);

        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("Order", customer);
        }

        return orders;
    }

    public List<Order> getOrdersBetweenDates(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByCreatedAtBetween(start, end);
    }

    @Transactional
    public OrderResponseDto createOrder(CreateOrderRequestDto orderDto) {

        Order order = orderMapper.toEntity(orderDto);

        Order savedOrder = orderRepository.save(order);

        return orderMapper.toDto(savedOrder);
    }

    @Transactional
    public Order updateOrder(Long id, Order updatedOrder) {
        Order currOrder = getOrderById(id);

        currOrder.setCustomer(updatedOrder.getCustomer());
        currOrder.setShipment(updatedOrder.getShipment());
        return orderRepository.save(currOrder);
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}
