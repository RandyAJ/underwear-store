package com.underwearstore.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private boolean available;
    private Integer quantity;
    private BigDecimal price;
    private Integer sale;
}
