package com.underwearstore.orderservice.service;

import com.underwearstore.inventoryservice.grpc.ProductRequest;
import com.underwearstore.inventoryservice.grpc.ProductResponse;
import com.underwearstore.orderservice.dto.ProductResponseDto;
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

    public ProductResponseDto checkAvailability(Long id){
        ProductRequest request = ProductRequest.newBuilder().setId(id).build();
        ProductResponse response = inventoryGrpcClient.checkAvailability(request);

        if(response.getQuantity() > 1){
            create(response);
        }

        return new ProductResponseDto(
                response.getId(), response.getName(), response.getAvailable(), response.getQuantity(),
                new BigDecimal(response.getPrice()), response.getSale()
        );
    }

    public Order create(ProductResponse productResponse){
        BigDecimal price = new BigDecimal(productResponse.getPrice());
        BigDecimal totalPrice = price.multiply(
                BigDecimal.valueOf(productResponse.getQuantity())
        );

        Order order = new Order(
                null, productResponse.getId(), productResponse.getName(),
                new BigDecimal(productResponse.getPrice()), totalPrice, productResponse.getQuantity()
        );

        return orderRepository.save(order);
    }
}
