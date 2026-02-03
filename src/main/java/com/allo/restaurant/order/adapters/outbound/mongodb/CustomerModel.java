package com.allo.restaurant.order.adapters.outbound.mongodb;

import org.springframework.data.mongodb.core.mapping.Field;

public record CustomerModel(
        @Field("full_name")
        String fullName,
        
        @Field("address")
        String address,
        
        @Field("email")
        String email
) {
}
