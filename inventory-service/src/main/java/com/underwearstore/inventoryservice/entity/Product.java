package com.underwearstore.inventoryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // ID генерируется до Insert в БД. Можно использовать пакетную вставку (batching). Можно прикрутить контроль над генерацией ID
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer sale; // В процентах от 0 до 100. Позже добавить допустимый диапазон

}
