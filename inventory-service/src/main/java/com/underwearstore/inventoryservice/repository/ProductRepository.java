package com.underwearstore.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.underwearstore.inventoryservice.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Optional<Product> findById(Long id);

    List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max);

    List<Product> findAllByPriceLessThan(BigDecimal max); // where x.price < max

    List<Product> findAllByPriceGreaterThan(BigDecimal min); // where x.price > min

}