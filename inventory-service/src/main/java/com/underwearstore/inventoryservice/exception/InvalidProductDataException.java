package com.underwearstore.inventoryservice.exception;

import lombok.Getter;

@Getter
public class InvalidProductDataException extends RuntimeException {
    private final long id;

    public InvalidProductDataException(String message, long id){
        super(message);

        this.id = id;
    }
}
