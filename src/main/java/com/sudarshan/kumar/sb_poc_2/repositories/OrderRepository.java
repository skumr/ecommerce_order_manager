package com.sudarshan.kumar.sb_poc_2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.kumar.sb_poc_2.models.Order;
import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.models.Shipment;


public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByCustomer(Customer customer);

    Optional<Order> findByShipment(Shipment shipment);
}
