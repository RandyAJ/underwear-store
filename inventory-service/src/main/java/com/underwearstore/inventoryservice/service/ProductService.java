package com.underwearstore.inventoryservice.service;

import com.underwearstore.inventoryservice.entity.Product;
import com.underwearstore.inventoryservice.repository.ProductRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Product get(Long id){
        if (id == null) {
            throw new IllegalArgumentException("ID при поиске Task не может быть null");
        }

        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Product with ID %d not found ~ ", id)));
    }

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Boolean delete(Long id){
        try {
            productRepository.delete(this.get(id));

            return true;

        } catch (RuntimeException e) {
            throw new RuntimeException(String.format("Exception during deletion resource with ID %d ~ ", id) + e);
        }
    }

    public Product checkAvailability(Long id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            System.out.printf("Product with ID %s not found ~ %n", id);

            throw new RuntimeException(String.format("Product with ID %s not found ~ %n", id));
        }

        return product.get();
    }
}
