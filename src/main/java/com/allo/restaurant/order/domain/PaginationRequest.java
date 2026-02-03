package com.allo.restaurant.order.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PaginationRequest(
        @Min(value = 0, message = "Offset must be non-negative")
        int offset,
        
        @Min(value = 1, message = "Size must be at least 1")
        @Max(value = 100, message = "Size must not exceed 100")
        int size
) {
    public static PaginationRequest of(int offset, int size) {
        return new PaginationRequest(offset, size);
    }
    
    public static PaginationRequest defaultPagination() {
        return new PaginationRequest(0, 10);
    }
}
