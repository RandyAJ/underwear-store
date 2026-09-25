package com.underwearstore.inventoryservice.repository;

import com.underwearstore.inventoryservice.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
