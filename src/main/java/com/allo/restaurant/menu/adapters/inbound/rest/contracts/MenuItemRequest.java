package com.allo.restaurant.menu.adapters.inbound.rest.contracts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record MenuItemRequest(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("price") BigDecimal price
) {
    @JsonCreator
    public MenuItemRequest {
    }
}
