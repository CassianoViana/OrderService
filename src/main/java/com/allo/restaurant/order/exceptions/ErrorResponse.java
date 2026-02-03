package com.allo.restaurant.order.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String message,
        Map<String, String> errors,
        LocalDateTime timestamp
) {
}
