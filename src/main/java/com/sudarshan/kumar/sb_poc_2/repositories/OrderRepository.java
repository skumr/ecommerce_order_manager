package com.sudarshan.kumar.sb_poc_2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sudarshan.kumar.sb_poc_2.models.Customer;
import com.sudarshan.kumar.sb_poc_2.models.Order;
import com.sudarshan.kumar.sb_poc_2.models.Shipment;


public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomer(Customer customer);

    List<Order> findByShipment(Shipment shipment);
}
