package com.allo.restaurant.order.adapters.outbound.rabbitmq;

import com.allo.restaurant.order.config.RabbitMQConfig;
import com.allo.restaurant.order.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderNotificationService {

    private final RabbitTemplate rabbitTemplate;

    public void publishStatusChange(Order order) {
        log.info("Publishing status change for order: {}", order.id());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "order.status.changed", order);
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_STATUS_QUEUE)
    public void consumeStatusChange(Order order) {
        log.info("=== ORDER NOTIFICATION SENT ===");
        log.info("Order ID: {}", order.id());
        log.info("Customer Full Name: {}", order.customer().fullName());
        log.info("Customer Address: {}", order.customer().address());
        log.info("Customer Email: {}", order.customer().email());
        log.info("New Status: {}", order.status());
        log.info("Updated At: {}", order.updatedAt());
        log.info("Total Amount: ${}", order.totalAmount());
        log.info("================================");
    }
}