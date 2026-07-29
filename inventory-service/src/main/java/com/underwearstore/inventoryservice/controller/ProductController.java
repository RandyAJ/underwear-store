package com.underwearstore.inventoryservice.controller;

import com.underwearstore.inventoryservice.entity.Product;
import com.underwearstore.inventoryservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productService.get(id);
    }

    @GetMapping
    public List<Product> list(){
        return productService.list();
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Long id){
        return productService.delete(id);
    }
}
