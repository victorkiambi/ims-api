package com.ims.imsapi.repository;

import com.ims.imsapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByCustomer_id(Long customerId);
}
