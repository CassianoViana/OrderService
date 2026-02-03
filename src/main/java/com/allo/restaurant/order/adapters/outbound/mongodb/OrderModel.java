package com.allo.restaurant.order.adapters.outbound.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
public record OrderModel(
        @Id
        String id,
        @Field("customer")
        CustomerModel customer,
        @Field("order_items")
        List<OrderItemModel> orderItems,
        @Field("total_amount")
        BigDecimal totalAmount,
        @Field("status")
        String status,
        @Field("created_at")
        LocalDateTime createdAt,
        @Field("updated_at")
        LocalDateTime updatedAt
) {
}
