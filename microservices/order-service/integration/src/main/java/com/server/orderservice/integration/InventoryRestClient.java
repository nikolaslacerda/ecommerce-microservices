package com.server.orderservice.integration;

import com.server.orderservice.integration.client.InventoryClient;
import com.server.orderservice.integration.model.OrderRestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class InventoryRestClient {

    private final InventoryClient inventoryClient;
    private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    public boolean isAllItemsInStock(OrderRestRequest order) {
        Supplier<Boolean> isAllItemsInStock = () ->
                order.getItems().stream().allMatch(item -> inventoryClient.checkStock(item.getSku()));
        Resilience4JCircuitBreaker circuitBreaker = circuitBreakerFactory.create("inventory");
        return circuitBreaker.run(isAllItemsInStock, throwable -> handleErrorCase());
    }

    private boolean handleErrorCase() {
        return false;
    }
}
