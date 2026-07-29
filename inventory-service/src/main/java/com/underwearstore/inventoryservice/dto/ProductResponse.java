package com.underwearstore.inventoryservice.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        Integer quantity,
        Integer sale
) {
    public Integer getQuantity(){
        return this.quantity();
    }
}
