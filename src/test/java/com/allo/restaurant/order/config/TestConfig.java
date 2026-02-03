package com.allo.restaurant.order.config;

import com.allo.restaurant.order.utils.MockHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public HttpClient mockHttpClient() throws Exception {
        HttpClient mockHttpClient = mock(HttpClient.class);
        
        String pizzaResponse = MockHelper.readJsonFile("mock-menu-item-response.json");
        String drinkResponse = MockHelper.readJsonFile("mock-drink-item-response.json");
        
        HttpResponse<String> pizzaHttpResponse = mock(HttpResponse.class);
        when(pizzaHttpResponse.statusCode()).thenReturn(200);
        when(pizzaHttpResponse.body()).thenReturn(pizzaResponse);
        
        HttpResponse<String> drinkHttpResponse = mock(HttpResponse.class);
        when(drinkHttpResponse.statusCode()).thenReturn(200);
        when(drinkHttpResponse.body()).thenReturn(drinkResponse);
        
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(pizzaHttpResponse)
                .thenReturn(drinkHttpResponse);
        
        return mockHttpClient;
    }

    @Bean
    @Primary
    public ObjectMapper testObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        com.fasterxml.jackson.datatype.jsr310.JavaTimeModule javaTimeModule = new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule();
        javaTimeModule.addSerializer(java.time.LocalDateTime.class, 
            new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer(
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }
}
