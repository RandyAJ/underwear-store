package com.underwearstore.inventoryservice.grpc;

import com.underwearstore.inventoryservice.service.ProductService;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class InventoryServiceImpl extends InventoryServiceGrpc.InventoryServiceImplBase {
    private final ProductService productService;

    public InventoryServiceImpl(ProductService productService){
        this.productService = productService;
    }

    @Override
    public void checkAvailability(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Boolean available = productService.checkAvailability(request.getId());

        ProductResponse response = ProductResponse.newBuilder()
            .setAvailable(available)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
