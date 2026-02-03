package com.allo.restaurant.order.adapters.outbound.mongodb;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

public record OrderItemModel(
        @Field("product_id")
        String productId,
        
        @Field("name")
        String name,
        
        @Field("quantity")
        Integer quantity,
        
        @Field("price")
        BigDecimal price
) {
}
