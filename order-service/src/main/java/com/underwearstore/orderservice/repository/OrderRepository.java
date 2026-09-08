package com.underwearstore.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.underwearstore.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
}
