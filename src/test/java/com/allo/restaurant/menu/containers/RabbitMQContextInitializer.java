package com.allo.restaurant.menu.containers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.RabbitMQContainer;

public class RabbitMQContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final RabbitMQContainer RABBITMQ_CONTAINER = new RabbitMQContainer("rabbitmq:3-management");

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        if (!RABBITMQ_CONTAINER.isRunning()) {
            RABBITMQ_CONTAINER.start();
        }

        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                context,
                "spring.rabbitmq.host=" + RABBITMQ_CONTAINER.getHost(),
                "spring.rabbitmq.port=" + RABBITMQ_CONTAINER.getAmqpPort(),
                "spring.rabbitmq.username=" + RABBITMQ_CONTAINER.getAdminUsername(),
                "spring.rabbitmq.password=" + RABBITMQ_CONTAINER.getAdminPassword()
        );
    }
}