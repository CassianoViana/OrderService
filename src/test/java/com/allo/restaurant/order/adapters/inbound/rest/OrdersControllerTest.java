package com.allo.restaurant.order.adapters.inbound.rest;

import com.allo.restaurant.menu.annotations.IntegrationTest;
import com.allo.restaurant.order.utils.MockHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockHelper mockHelper;

    @MockitoBean
    private HttpClient httpClient;

    @Test
    void shouldCreateOrderAndUpdateStatusSuccessfully() throws Exception {
        String validOrderRequestJson = MockHelper.readJsonFile("valid-order-request.json");

        mockHelper.mockMenuHttpGetItem(httpClient, "mock-menu-item-response.json");

        MvcResult result = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validOrderRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.customer.fullName").value("John Doe"))
                .andExpect(jsonPath("$.customer.address").value("123 Main St"))
                .andExpect(jsonPath("$.customer.email").value("john@example.com"))
                .andExpect(jsonPath("$.status").value("CREATED"))
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.updatedAt").exists())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        String createdOrderId = objectMapper.readTree(response).get("id").asText();
        assertNotNull(createdOrderId);

        mockMvc.perform(patch("/orders/%s/status".formatted(createdOrderId))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "status":"READY"
                        }
                        """
                )).andExpect(status().isOk());
    }

    @Test
    void shouldGetOrderByIdSuccessfully() throws Exception {

        String validOrderRequestJson = MockHelper.readJsonFile("valid-order-request.json");

        mockHelper.mockMenuHttpGetItem(httpClient, "mock-menu-item-response.json");

        String result = mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validOrderRequestJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String orderId = objectMapper.readTree(result).get("id").asText();

        mockMvc.perform(get("/orders/" + orderId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.customer.fullName").value("John Doe"))
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    void shouldReturn404WhenOrderNotFound() throws Exception {
        mockMvc.perform(get("/orders/nonexistent-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn404WhenUpdatingNonexistentOrderStatus() throws Exception {
        String statusUpdateRequestJson = MockHelper.readJsonFile("status-update-request.json");

        mockMvc.perform(patch("/orders/nonexistent-id/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(statusUpdateRequestJson))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetOrdersWithPagination() throws Exception {
        String validOrderRequestJson = MockHelper.readJsonFile("valid-order-request.json");

        mockHelper.mockMenuHttpGetItem(httpClient, "mock-menu-item-response.json");

        for (int i = 0; i < 5; i++) {
            mockMvc.perform(post("/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(validOrderRequestJson))
                    .andExpect(status().isCreated());
        }

        mockMvc.perform(get("/orders")
                        .param("limit", "3")
                        .param("offset", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orders").isArray())
                .andExpect(jsonPath("$.orders.length()").value(3))
                .andExpect(jsonPath("$.limit").value(3))
                .andExpect(jsonPath("$.offset").value(0))
                .andExpect(jsonPath("$.totalRecords").value(5));
    }

    @Test
    void shouldValidateOrderRequest() throws Exception {
        String invalidOrderRequestJson = MockHelper.readJsonFile("invalid-order-request.json");

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidOrderRequestJson))
                .andExpect(status().isBadRequest());
    }

}