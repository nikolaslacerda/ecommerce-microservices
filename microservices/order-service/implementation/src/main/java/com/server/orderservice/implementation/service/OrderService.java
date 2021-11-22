package com.server.orderservice.implementation.service;

import com.server.orderservice.implementation.dto.OrderDto;
import com.server.orderservice.implementation.mapper.OrderMapper;
import com.server.orderservice.implementation.model.Order;
import com.server.orderservice.implementation.repository.OrderRepository;
import com.server.orderservice.integration.InventoryRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final StreamBridge streamBridge;
    private final InventoryRestClient restClient;
    private final OrderRepository orderRepository;

    public String createOrder(OrderDto orderDto) {
        if (restClient.isAllItemsInStock(OrderMapper.mapToRest(orderDto))) {
            Order order = OrderMapper.mapToModel(orderDto);
            order.setOrderNumber(UUID.randomUUID().toString());
            order.getItems().forEach(item -> item.setOrder(order));
            Order orderCreated = orderRepository.save(order);
            streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(order.getId()).build());
            return orderCreated.getOrderNumber();
        } else {
            return "One of products in the order is not in stock";
        }
    }


}
