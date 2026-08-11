package com.underwearstore.orderservice.service;

import com.underwearstore.inventoryservice.grpc.ProductRequest;
import com.underwearstore.inventoryservice.grpc.ProductResponse;
import com.underwearstore.orderservice.dto.ProductResponseDto;
import com.underwearstore.orderservice.grpc.InventoryGrpcClient;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class OrderService {
    private final InventoryGrpcClient inventoryGrpcClient;

    public OrderService (InventoryGrpcClient inventoryGrpcClient){
        this.inventoryGrpcClient = inventoryGrpcClient;
    }

    public ProductResponseDto checkAvailability(Long id){
        ProductRequest request = ProductRequest.newBuilder().setId(id).build();
        System.out.println("ORDER SERVICE: id = " + id);
        ProductResponse response = inventoryGrpcClient.checkAvailability(request);

        return new ProductResponseDto(
                response.getId(), response.getName(), response.getAvailable(), response.getQuantity(),
                new BigDecimal(response.getPrice()), response.getSale()
        );
    }
}
