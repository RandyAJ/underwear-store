package com.underwearstore.orderservice.service;

import com.underwearstore.grpc.ProductRequest;
import com.underwearstore.grpc.ProductResponse;
import com.underwearstore.orderservice.entity.Order;
import com.underwearstore.orderservice.grpc.InventoryGrpcClient;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import com.underwearstore.orderservice.repository.OrderRepository;

@Service
public class OrderService {
    private final InventoryGrpcClient inventoryGrpcClient;
    private final OrderRepository orderRepository;

    public OrderService (InventoryGrpcClient inventoryGrpcClient, OrderRepository orderRepository){
        this.inventoryGrpcClient = inventoryGrpcClient;
        this.orderRepository = orderRepository;
    }

    public Order checkAvailability(Long id, Integer quantityOrdered){
        ProductRequest request = ProductRequest.newBuilder()
                .setId(id)
                .setQuantityOrdered(quantityOrdered)
                .build();

        ProductResponse response = inventoryGrpcClient.checkAvailability(request);

        return create(response, quantityOrdered);
    }

    public Order create(ProductResponse productResponse, Integer quantityOrdered){
        BigDecimal totalPrice = new BigDecimal(
                productResponse.getPrice()).multiply(
                    BigDecimal.valueOf(productResponse.getQuantity()
                )
        );

        Order order = new Order(
                null, productResponse.getId(), productResponse.getName(),
                new BigDecimal(productResponse.getPrice()), totalPrice, quantityOrdered
        );

        orderRepository.save(order);

        return order;
    }
}
