package com.allo.restaurant.order.adapters.inbound.rest.contracts;

public record CustomerResponse(
        String fullName,
        String address,
        String email
) {}
