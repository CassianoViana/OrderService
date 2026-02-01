package com.allo.restaurant.menu.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MockHelper {

    private final ObjectMapper objectMapper;

    public MockHelper() {
        this.objectMapper = new ObjectMapper();
    }

    public String readFile(String filePath) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IOException("File not found in test resources: " + filePath);
            }
            return new String(inputStream.readAllBytes());
        }
    }

    public <T> T readFileAs(String filePath, Class<T> valueType) throws IOException {
        String content = readFile(filePath);
        return objectMapper.readValue(content, valueType);
    }
}
