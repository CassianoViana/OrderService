package com.allo.restaurant.order.adapters.inbound.rest.contracts;

import java.math.BigDecimal;

public record OrderItemResponse(
        String productId,
        String name,
        Integer quantity,
        BigDecimal price
) {
}
