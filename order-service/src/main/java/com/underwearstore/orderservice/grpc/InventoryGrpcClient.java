package com.underwearstore.orderservice.grpc;

import org.springframework.stereotype.Service;
import com.underwearstore.inventoryservice.grpc.InventoryServiceGrpc;
import com.underwearstore.inventoryservice.grpc.ProductRequest;
import com.underwearstore.inventoryservice.grpc.ProductResponse;

@Service
public class InventoryGrpcClient {

    private final InventoryServiceGrpc.InventoryServiceBlockingStub stub;

    public InventoryGrpcClient(InventoryServiceGrpc.InventoryServiceBlockingStub stub) {
        this.stub = stub;
    }

    public ProductResponse checkAvailability(ProductRequest request){
        return stub.checkAvailability(request);
    }

}
