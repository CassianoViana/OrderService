package com.allo.restaurant.menu.ports.outbound;

import java.time.LocalDateTime;

public interface TimeProvider {
    LocalDateTime now();
}
