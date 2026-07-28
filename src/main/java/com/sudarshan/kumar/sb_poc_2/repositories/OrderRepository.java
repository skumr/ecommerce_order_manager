package com.sudarshan.kumar.sb_poc_2.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.kumar.sb_poc_2.enums.OrderStatusEnum;
import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.models.Order;
import com.sudarshan.kumar.sb_poc_2.models.Shipment;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);

    List<Order> findByCustomerOrderByCreatedAtDesc(Customer customer);

    List<Order> findByShipment(Shipment shipment);

    Optional<Order> findByShipmentTrackingNumber(String trackingNumber);

    List<Order> findByOrderStatus(OrderStatusEnum orderStatus);

    List<Order> findByOrderStatusAndCreatedAtBefore(OrderStatusEnum orderStatus, LocalDateTime date);

    List<Order> findByOrderStatusAndShipmentIsNull(OrderStatusEnum orderStatus);

    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Order> findTop10ByCustomerOrderByCreatedAtDesc(Customer customer);

    long countByOrderStatus(OrderStatusEnum orderStatus);
}