package com.allo.restaurant.order.domain;

import java.util.List;

public record GenericPaginatedResult<T>(
        List<T> items,
        int limit,
        int offset,
        long totalRecords
) {
}
