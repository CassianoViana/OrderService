package com.allo.restaurant.order.domain;

import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@With
public record Order(
        String id,
        Customer customer,
        List<OrderItem> orderItems,
        BigDecimal totalAmount,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public enum OrderStatus {
        CREATED, CONFIRMED, PREPARING, READY, COMPLETED, CANCELLED
    }
    
    public static BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

