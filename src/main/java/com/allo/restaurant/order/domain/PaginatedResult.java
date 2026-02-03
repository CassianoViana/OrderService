package com.allo.restaurant.order.domain;

import java.util.List;

public record PaginatedResult(
        List<MenuItem> items,
        long totalRecords
) {
}
