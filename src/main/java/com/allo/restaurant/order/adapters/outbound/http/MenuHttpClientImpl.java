package com.allo.restaurant.order.adapters.outbound.http;

import com.allo.restaurant.order.domain.MenuItem;
import com.allo.restaurant.order.ports.outbound.MenuItemUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class MenuHttpClientImpl implements MenuItemUseCase {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${menu.service.url}")
    private String menuServiceUrl;

    public MenuHttpClientImpl(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public MenuItem getMenuItem(String id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(menuServiceUrl + "/menu-items/" + id))
                    .timeout(Duration.ofSeconds(10))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), MenuItem.class);
            } else {
                throw new RuntimeException("Failed to fetch menu item. Status: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error fetching menu item", e);
        }
    }
}
