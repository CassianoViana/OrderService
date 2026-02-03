package com.allo.restaurant.order.ports.outbound;

import com.allo.restaurant.order.domain.Order;
import com.allo.restaurant.order.domain.PaginationRequest;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);
    Order findById(String id);
    List<Order> findAll(PaginationRequest pagination);
    long count();
}
