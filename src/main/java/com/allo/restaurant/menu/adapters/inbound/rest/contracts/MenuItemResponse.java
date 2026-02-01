package com.allo.restaurant.menu.adapters.inbound.rest.contracts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MenuItemResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("price") BigDecimal price,
        @JsonProperty("createdAt") LocalDateTime createdAt
) {
    @JsonCreator
    public MenuItemResponse {
    }
}