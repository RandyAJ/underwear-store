package com.underwearstore.inventoryservice.controller;

import com.underwearstore.inventoryservice.service.StoreService;
import com.underwearstore.inventoryservice.entity.Store;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }
//        @PostMapping
//        public Store create(@RequestBody Store store) {
//            return storeService.create(store);
//        }
//
//        @GetMapping("/{id}")
//        public Store get(@PathVariable Long id) {
//            return storeService.get(id);
//        }
//
//        @GetMapping
//        public List<Store> list(){
//            return storeService.list();
//        }
//
//        @DeleteMapping("/{id}")
//        public Boolean delete(@PathVariable Long id){
//            return storeService.delete(id);
//        }
//    }
}
