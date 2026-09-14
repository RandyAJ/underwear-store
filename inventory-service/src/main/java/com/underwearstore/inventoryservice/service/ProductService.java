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

    public Product checkAvailability(Long id, Integer quantityOrdered){ //
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            String message = "Товар отсутствует, Id: " + id;
            throw new RuntimeException(message);
        }

        if(product.get().getQuantity() < 1){
            String message = "Товар отсутствует для создания заказа. Id продукта: " + id + " его количество: 0";
            throw new RuntimeException(message);
        }

        if(product.get().getQuantity() < quantityOrdered){
            String message = "Заказываемое количетсво превышает количество товара на складе. Id продукта: " + id + " и заказываемое количество: " + quantityOrdered;
            throw new RuntimeException(message);
        }

        return product.get();
    }
}
