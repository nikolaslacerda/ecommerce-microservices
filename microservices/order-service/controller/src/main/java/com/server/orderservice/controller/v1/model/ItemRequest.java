package com.server.orderservice.controller.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequest {

    private String sku;
    private BigDecimal price;
    private Integer quantity;
}
