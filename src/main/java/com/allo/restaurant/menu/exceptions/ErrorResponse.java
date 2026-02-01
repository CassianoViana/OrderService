package com.allo.restaurant.menu.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String message,
        Map<String, String> errors,
        LocalDateTime timestamp
) {
}
