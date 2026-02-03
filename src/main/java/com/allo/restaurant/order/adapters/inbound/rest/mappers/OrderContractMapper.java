package com.allo.restaurant.order.adapters.inbound.rest.mappers;

import com.allo.restaurant.order.adapters.inbound.rest.contracts.OrderRequest;
import com.allo.restaurant.order.adapters.inbound.rest.contracts.OrderResponse;
import com.allo.restaurant.order.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderContractMapper {

    Order fromRequestToDomain(OrderRequest request);

    OrderResponse fromDomainToResponse(Order order);
}
