package com.allo.restaurant.menu.adapters.inbound.rest.contracts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PaginatedMenuItemResponse(
        @JsonProperty("items") List<MenuItemResponse> items,
        @JsonProperty("totalRecords") long totalRecords
) {
    @JsonCreator
    public PaginatedMenuItemResponse {
    }
}
