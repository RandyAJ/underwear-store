package com.underwearstore.inventoryservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stores")
@NoArgsConstructor
@Setter
@Getter
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // ID генерируется до Insert в БД. Можно использовать пакетную вставку (batching). Можно прикрутить контроль над генерацией ID
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;
}
