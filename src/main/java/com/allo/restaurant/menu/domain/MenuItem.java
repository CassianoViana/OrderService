package com.allo.restaurant.menu.domain;

import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@With
public record MenuItem(
        String id,
        String name,
        String description,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
