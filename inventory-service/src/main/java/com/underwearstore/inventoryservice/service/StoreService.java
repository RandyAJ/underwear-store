package com.underwearstore.inventoryservice.service;

import com.underwearstore.inventoryservice.entity.Store;
import com.underwearstore.inventoryservice.repository.StoreRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store create(Store store){
        return storeRepository.save(store);
    }

    public Store get(Long id){
        if (id == null) {
            throw new IllegalArgumentException("ID при поиске Store не может быть null");
        }

        return storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Store with ID %d not found ~ ", id)));
    }

    public List<Store> list(){
        return storeRepository.findAll();
    }

    public Boolean delete(Long id){
        try {
            storeRepository.delete(this.get(id));

            return true;

        } catch (RuntimeException e) {
            throw new RuntimeException(String.format("Exception during deletion Store with ID %d ~ ", id) + e);
        }
    }
}
