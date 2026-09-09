package com.underwearstore.orderservice.service;

import com.underwearstore.grpc.ProductRequest;
import com.underwearstore.grpc.ProductResponse;
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

    public ProductResponseDto checkAvailability(Long id, Integer quantity){
        ProductRequest request = ProductRequest.newBuilder()
                .setId(id)
                .setQuantity(quantity)
                .build();

        ProductResponse response = inventoryGrpcClient.checkAvailability(request);

        if(response.getQuantity() > 1){
            create(response); // каждая проверка доступности влечет создание записи в бд = плохо. добавить входной аргумент количества заказываемого товара и переделать дальнейшую логику ЗАКАЗА.
        }

        return new ProductResponseDto(
                response.getId(), response.getName(), response.getAvailable(), response.getQuantity(),
                new BigDecimal(response.getPrice()), response.getSale()
        );
    }

    public void create(ProductResponse productResponse){
        BigDecimal totalPrice = new BigDecimal(productResponse.getPrice()).multiply(
                BigDecimal.valueOf(productResponse.getQuantity())
        );

        Order order = new Order(
                null, productResponse.getId(), productResponse.getName(),
                new BigDecimal(productResponse.getPrice()), totalPrice, productResponse.getQuantity()
        );

        orderRepository.save(order);
    }
}
