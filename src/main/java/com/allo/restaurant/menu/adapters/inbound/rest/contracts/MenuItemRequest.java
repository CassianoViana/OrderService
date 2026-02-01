package com.allo.restaurant.menu.adapters.inbound.rest.contracts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record MenuItemRequest(
        @JsonProperty("name")
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        String name,
        @JsonProperty("description")
        @NotBlank(message = "Description is required")
        @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
        String description,
        @JsonProperty("price")
        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
        @Digits(integer = 10, fraction = 2, message = "Price must have at most 2 decimal places")
        BigDecimal price
) {
    @JsonCreator
    public MenuItemRequest {
    }
}
