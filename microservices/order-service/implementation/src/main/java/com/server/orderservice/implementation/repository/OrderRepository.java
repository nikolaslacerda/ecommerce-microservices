package com.server.orderservice.implementation.repository;

import com.server.orderservice.implementation.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
