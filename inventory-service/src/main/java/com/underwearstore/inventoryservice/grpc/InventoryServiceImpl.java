package com.underwearstore.inventoryservice.grpc;

import com.underwearstore.inventoryservice.entity.Product;
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
        try {
            Product product = productService.checkAvailability(request.getId());

            ProductResponse response = ProductResponse.newBuilder()
                    .setAvailable(product.getQuantity() > 0)
                    .setQuantity(product.getQuantity())

                    .setId(product.getId())
                    .setName(product.getName())
                    .setPrice(String.valueOf(product.getPrice()))
                    .setSale(product.getSale())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (RuntimeException e){
            responseObserver.onError(
                    io.grpc.Status.NOT_FOUND
                        .withDescription(e.getMessage())
                        .asRuntimeException()

            );
        }
    }
}
