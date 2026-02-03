package com.allo.restaurant.order.adapters.inbound.rest.contracts;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderHistoryResponse(
        List<OrderResponse> orders,
        int limit,
        int offset,
        long totalRecords
) {
}
