package com.underwearstore.inventoryservice.exception;

import lombok.Getter;

@Getter
public class ProductOutOfStockException extends RuntimeException {
    private final long id;
    private final int productQuantity;

    public ProductOutOfStockException(String message, long id, int productQuantity){
        super(message);

        this.id = id;
        this.productQuantity = productQuantity;
    }
}
