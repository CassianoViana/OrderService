package com.allo.restaurant.order.adapters.inbound.rest.contracts;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record OrderRequest(
        @Valid
        @NotNull(message = "Customer information is required")
        CustomerRequest customer,
        
        @NotEmpty(message = "Order items cannot be empty")
        @Valid
        List<OrderItemRequest> orderItems
) {}

