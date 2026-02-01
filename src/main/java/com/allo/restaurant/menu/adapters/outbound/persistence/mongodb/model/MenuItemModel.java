package com.allo.restaurant.menu.adapters.outbound.persistence.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "menu_items")
public record MenuItemModel(
        @Id
        String id,
        String name,
        String description,
        BigDecimal price,
        LocalDateTime createdAt
) {
}
