package com.server.inventaryservice.service;

import com.server.inventaryservice.model.Inventory;
import com.server.inventaryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Boolean isInStock(String sku) {
        Inventory inventory = inventoryRepository.getBySku(sku)
                .orElseThrow(() -> new RuntimeException("Cannot find product by sku code " + sku));
        return inventory.getStock() > 0;
    }
}
