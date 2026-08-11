package com.underwearstore.orderservice.controller;

import com.underwearstore.inventoryservice.grpc.ProductResponse;
import com.underwearstore.orderservice.dto.ProductResponseDto;
import com.underwearstore.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    // тест gRPC
    @GetMapping("/{id}")
    public ProductResponseDto checkAvailability(@PathVariable Long id){
        return orderService.checkAvailability(id);
    }
}
