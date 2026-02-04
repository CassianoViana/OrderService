package com.allo.restaurant.menu.annotations;

import com.allo.restaurant.menu.containers.MongoDBContextInitializer;
import com.allo.restaurant.menu.containers.RabbitMQContextInitializer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {MongoDBContextInitializer.class, RabbitMQContextInitializer.class})
public @interface IntegrationTest {
}
