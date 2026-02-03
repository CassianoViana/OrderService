package com.allo.restaurant.order.application.usecases;

import com.allo.restaurant.order.adapters.outbound.rabbitmq.OrderNotificationService;
import com.allo.restaurant.order.domain.GenericPaginatedResult;
import com.allo.restaurant.order.domain.MenuItem;
import com.allo.restaurant.order.domain.Order;
import com.allo.restaurant.order.domain.PaginationRequest;
import com.allo.restaurant.order.ports.outbound.MenuItemUseCase;
import com.allo.restaurant.order.ports.inbound.OrderUseCase;
import com.allo.restaurant.order.ports.outbound.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderUseCaseService implements OrderUseCase {

    private final MenuItemUseCase menuUseCase;
    private final OrderRepository orderRepository;
    private final OrderNotificationService orderNotificationService;

    @Override
    public Order createOrder(Order order) {

        var updatedOrderItems = order.orderItems()
                .stream().map(item -> {
                    MenuItem menuItem = menuUseCase.getMenuItem(item.productId());
                    return item.withPrice(menuItem.price()).withName(menuItem.name());
                }).collect(Collectors.toList());

        var orderWithItems = order.withOrderItems(updatedOrderItems)
                .withCreatedAt(LocalDateTime.now())
                .withUpdatedAt(LocalDateTime.now())
                .withStatus(Order.OrderStatus.CREATED)
                .withTotalAmount(Order.calculateTotalAmount(updatedOrderItems));

        Order createdOrder = orderRepository.save(orderWithItems);
        
        orderNotificationService.publishStatusChange(createdOrder);
        
        return createdOrder;
    }

    @Override
    public Order getOrder(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order updateOrderStatus(String id, Order.OrderStatus status) {
        Order existingOrder = orderRepository.findById(id);
        if (existingOrder == null) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        
        Order updatedOrder = existingOrder
                .withStatus(status)
                .withUpdatedAt(LocalDateTime.now());
        
        Order savedOrder = orderRepository.save(updatedOrder);
        
        orderNotificationService.publishStatusChange(savedOrder);
        
        return savedOrder;
    }

    @Override
    public GenericPaginatedResult<Order> getOrders(PaginationRequest pagination) {
        List<Order> orders = orderRepository.findAll(pagination);
        long totalRecords = orderRepository.count();
        
        return new GenericPaginatedResult<>(
                orders,
                pagination.size(),
                pagination.offset(),
                totalRecords
        );
    }
}
