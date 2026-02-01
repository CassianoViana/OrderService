package com.allo.restaurant.menu.adapters.inbound.rest;

import com.allo.restaurant.menu.annotations.IntegrationTest;
import com.allo.restaurant.menu.helpers.MockHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockHelper mockHelper;

    @Test
    void shouldCreateMenuItemSuccessfully() throws Exception {

        String requestBody = mockHelper.readFile("menu-item-request.json");
        String expected = mockHelper.readFile("menu-item-response.json");

        mockMvc.perform(post("/menu-items")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expected, true));
    }

}