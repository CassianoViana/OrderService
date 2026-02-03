package com.allo.restaurant.order.domain;

import lombok.With;

@With
public record Customer(
        String fullName,
        String address,
        String email
) {
}
