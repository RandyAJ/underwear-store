package com.underwearstore.orderservice.controller;

import com.underwearstore.orderservice.entity.Order;
import com.underwearstore.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public Order checkAvailability(@RequestParam Long id, @RequestParam Integer quantityOrdered){
        return orderService.checkAvailability(id, quantityOrdered);
    }
}
