package com.allo.restaurant.order.adapters.outbound.mongodb;

import com.allo.restaurant.order.adapters.outbound.mongodb.mapper.OrderModelMapper;
import com.allo.restaurant.order.domain.Order;
import com.allo.restaurant.order.domain.PaginationRequest;
import com.allo.restaurant.order.exceptions.NotFoundException;
import com.allo.restaurant.order.ports.outbound.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final MongoTemplate mongoTemplate;
    private final OrderModelMapper orderModelMapper;

    public OrderRepositoryImpl(MongoTemplate mongoTemplate, OrderModelMapper orderModelMapper) {
        this.mongoTemplate = mongoTemplate;
        this.orderModelMapper = orderModelMapper;
    }

    @Override
    public Order save(Order order) {
        OrderModel orderModel = orderModelMapper.toOrderModel(order);
        OrderModel savedOrder = mongoTemplate.save(orderModel);
        return orderModelMapper.toOrder(savedOrder);
    }

    @Override
    public Order findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return Optional.ofNullable(mongoTemplate.findOne(query, OrderModel.class))
                .map(orderModelMapper::toOrder)
                .orElseThrow(() -> new NotFoundException("Order %s not found".formatted(id)));
    }

    @Override
    public List<Order> findAll(PaginationRequest pagination) {
        Query query = new Query()
                .skip(pagination.offset())
                .limit(pagination.size())
                .with(Sort.by(Sort.Direction.DESC, "createdAt"));

        List<OrderModel> orderModels = mongoTemplate.find(query, OrderModel.class);
        return orderModels.stream()
                .map(orderModelMapper::toOrder)
                .toList();
    }

    @Override
    public long count() {
        return mongoTemplate.count(new Query(), OrderModel.class);
    }
}
