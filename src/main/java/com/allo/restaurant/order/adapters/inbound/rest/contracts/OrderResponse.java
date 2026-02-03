package com.allo.restaurant.order.adapters.inbound.rest.contracts;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponse(
        String id,
        CustomerResponse customer,
        List<OrderItemResponse> orderItems,
        BigDecimal totalAmount,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}

