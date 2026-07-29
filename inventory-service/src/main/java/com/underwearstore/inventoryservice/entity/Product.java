package com.underwearstore.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.AssertTrue;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // ID генерируется до Insert в БД. Можно использовать пакетную вставку (batching). Можно прикрутить контроль над генерацией ID
    private Long id;

    @NotNull
    @Setter
    @Getter
    private String name;

    @NotNull
    @Setter
    @Getter
    private String price;

    @NotNull
    @Setter
    @Getter
    private String photo;
}
