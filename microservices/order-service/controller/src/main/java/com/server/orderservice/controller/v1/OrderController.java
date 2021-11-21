package com.server.orderservice.controller.v1;

import com.server.orderservice.controller.v1.mapper.OrderMapper;
import com.server.orderservice.controller.v1.model.OrderRequest;
import com.server.orderservice.implementation.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody OrderRequest order) {
        return orderService.createOrder(OrderMapper.mapToDto(order));
    }
}
