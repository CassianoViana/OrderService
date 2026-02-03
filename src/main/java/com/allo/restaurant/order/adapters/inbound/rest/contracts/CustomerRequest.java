package com.allo.restaurant.order.adapters.inbound.rest.contracts;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "Full name is required")
        String fullName,
        
        @NotBlank(message = "Address is required")
        String address,
        
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email
) {}
