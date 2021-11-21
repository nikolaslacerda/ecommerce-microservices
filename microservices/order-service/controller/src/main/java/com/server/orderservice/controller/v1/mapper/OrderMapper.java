package com.server.orderservice.controller.v1.mapper;

import com.server.orderservice.controller.v1.model.ItemRequest;
import com.server.orderservice.controller.v1.model.OrderRequest;
import com.server.orderservice.implementation.dto.ItemDto;
import com.server.orderservice.implementation.dto.OrderDto;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto mapToDto(OrderRequest orderRequest) {
        return OrderDto.builder()
                .items(orderRequest.getItems().stream().map(OrderMapper::mapItemToDto).collect(Collectors.toList()))
                .build();
    }

    private static ItemDto mapItemToDto(ItemRequest item) {
        return ItemDto.builder()
                .sku(item.getSku())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }

}
