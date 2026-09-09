package com.underwearstore.orderservice.grpc;

import org.springframework.stereotype.Service;
import com.underwearstore.grpc.InventoryServiceGrpc;
import com.underwearstore.grpc.ProductRequest;
import com.underwearstore.grpc.ProductResponse;

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
