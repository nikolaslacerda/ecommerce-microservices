package com.server.orderservice.implementation.mapper;

import com.server.orderservice.implementation.dto.ItemDto;
import com.server.orderservice.implementation.dto.OrderDto;
import com.server.orderservice.integration.model.ItemRestRequest;
import com.server.orderservice.integration.model.OrderRestRequest;
import com.server.orderservice.implementation.model.Item;
import com.server.orderservice.implementation.model.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static Order mapToModel(OrderDto orderDto) {
        return Order.builder()
                .items(orderDto.getItems().stream().map(OrderMapper::mapItemToModel).collect(Collectors.toList()))
                .build();
    }

    public static Item mapItemToModel(ItemDto itemDto) {
        return Item.builder()
                .sku(itemDto.getSku())
                .price(itemDto.getPrice())
                .quantity(itemDto.getQuantity())
                .build();
    }

    public static OrderRestRequest mapToRest(OrderDto orderDto) {
        return OrderRestRequest.builder()
                .items(orderDto.getItems().stream().map(OrderMapper::mapItemToRest).collect(Collectors.toList()))
                .build();
    }

    private static ItemRestRequest mapItemToRest(ItemDto itemDto) {
        return ItemRestRequest.builder()
                .sku(itemDto.getSku())
                .price(itemDto.getPrice())
                .quantity(itemDto.getQuantity())
                .build();
    }
}
