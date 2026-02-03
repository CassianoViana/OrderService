package com.allo.restaurant.order.domain;

import lombok.With;

import java.math.BigDecimal;

@With
public record OrderItem(
        String productId,
        String name,
        Integer quantity,
        BigDecimal price
) {
}
