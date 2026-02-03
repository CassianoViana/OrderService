package com.allo.restaurant.order.adapters.outbound.mongodb.mapper;

import com.allo.restaurant.order.adapters.outbound.mongodb.CustomerModel;
import com.allo.restaurant.order.adapters.outbound.mongodb.OrderItemModel;
import com.allo.restaurant.order.adapters.outbound.mongodb.OrderModel;
import com.allo.restaurant.order.domain.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderModelMapper {

    OrderModel toOrderModel(Order order);
    Order toOrder(OrderModel orderModel);

}
