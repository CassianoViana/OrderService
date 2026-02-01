package com.allo.restaurant.menu.domain;

import java.util.List;

public record PaginatedResult(
        List<MenuItem> items,
        long totalRecords
) {
}
