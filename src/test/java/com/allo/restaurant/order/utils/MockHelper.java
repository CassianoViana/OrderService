package com.allo.restaurant.order.utils;

import org.springframework.stereotype.Component;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Component
public class MockHelper {

    public static String readJsonFile(String filename) throws Exception {
        File file = ResourceUtils.getFile("classpath:" + filename);
        return Files.readString(file.toPath());
    }

    public void mockMenuHttpGetItem(HttpClient httpClient, String expectedResponse) throws Exception {
        String pizzaResponse = readJsonFile(expectedResponse);

        HttpResponse<String> pizzaHttpResponse = mock(HttpResponse.class);
        when(pizzaHttpResponse.statusCode()).thenReturn(200);
        when(pizzaHttpResponse.body()).thenReturn(pizzaResponse);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(pizzaHttpResponse);
    }
}
