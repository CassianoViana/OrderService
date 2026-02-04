package com.allo.restaurant.menu.containers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MongoDBContainer;

public class MongoDBContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final MongoDBContainer MONGO_CONTAINER = new MongoDBContainer("mongo:latest");

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        if (!MONGO_CONTAINER.isRunning()) {
            MONGO_CONTAINER.start();
        }

        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                context,
                "spring.data.mongodb.uri=" + MONGO_CONTAINER.getReplicaSetUrl()
        );
    }
}