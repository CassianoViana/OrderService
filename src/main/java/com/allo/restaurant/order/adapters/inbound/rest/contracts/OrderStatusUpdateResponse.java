package com.allo.restaurant.order.adapters.inbound.rest.contracts;

import com.allo.restaurant.order.domain.Order;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderStatusUpdateResponse(
        String id,
        Order.OrderStatus status,
        LocalDateTime updatedAt
) {
}
