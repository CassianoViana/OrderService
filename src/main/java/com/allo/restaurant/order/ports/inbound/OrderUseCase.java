package com.allo.restaurant.order.ports.inbound;

import com.allo.restaurant.order.domain.GenericPaginatedResult;
import com.allo.restaurant.order.domain.Order;
import com.allo.restaurant.order.domain.PaginationRequest;

public interface OrderUseCase {
    Order createOrder(Order order);
    Order getOrder(String id);
    Order updateOrderStatus(String id, Order.OrderStatus status);
    GenericPaginatedResult<Order> getOrders(PaginationRequest pagination);
}
