package com.allo.restaurant.order.adapters.inbound.rest.contracts;

import com.allo.restaurant.order.domain.Order;
import jakarta.validation.constraints.NotNull;

public record OrderStatusUpdateRequest(
        @NotNull
        Order.OrderStatus status
) {
}
