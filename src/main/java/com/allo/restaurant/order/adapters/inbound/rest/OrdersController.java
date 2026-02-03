package com.allo.restaurant.order.adapters.inbound.rest;

import com.allo.restaurant.order.adapters.inbound.rest.contracts.OrderHistoryResponse;
import com.allo.restaurant.order.adapters.inbound.rest.contracts.OrderRequest;
import com.allo.restaurant.order.adapters.inbound.rest.contracts.OrderResponse;
import com.allo.restaurant.order.adapters.inbound.rest.contracts.OrderStatusUpdateRequest;
import com.allo.restaurant.order.adapters.inbound.rest.contracts.OrderStatusUpdateResponse;
import com.allo.restaurant.order.adapters.inbound.rest.mappers.OrderContractMapper;
import com.allo.restaurant.order.domain.GenericPaginatedResult;
import com.allo.restaurant.order.domain.Order;
import com.allo.restaurant.order.domain.PaginationRequest;
import com.allo.restaurant.order.ports.inbound.OrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrderUseCase orderUseCase;
    private final OrderContractMapper orderContractMapper;

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody @Valid OrderRequest request) {
        var domain = orderContractMapper.fromRequestToDomain(request);
        Order createdOrder = orderUseCase.createOrder(domain);
        return orderContractMapper.fromDomainToResponse(createdOrder);
    }

    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrder(@PathVariable String id) {
        Order order = orderUseCase.getOrder(id);
        return orderContractMapper.fromDomainToResponse(order);
    }

    @PatchMapping("/orders/{orderId}/status")
    @ResponseStatus(HttpStatus.OK)
    public OrderStatusUpdateResponse updateOrderStatus(
            @PathVariable String orderId,
            @RequestBody @Valid OrderStatusUpdateRequest request) {
        Order updatedOrder = orderUseCase.updateOrderStatus(orderId, request.status());
        return OrderStatusUpdateResponse.builder()
                .id(updatedOrder.id())
                .status(updatedOrder.status())
                .updatedAt(updatedOrder.updatedAt())
                .build();
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public OrderHistoryResponse getOrders(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        
        PaginationRequest pagination = PaginationRequest.of(offset, limit);
        GenericPaginatedResult<Order> result = orderUseCase.getOrders(pagination);
        
        return OrderHistoryResponse.builder()
                .orders(result.items().stream()
                        .map(orderContractMapper::fromDomainToResponse)
                        .toList())
                .limit(result.limit())
                .offset(result.offset())
                .totalRecords(result.totalRecords())
                .build();
    }
}
