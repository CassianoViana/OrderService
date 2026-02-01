package com.allo.restaurant.menu.adapters.outbound.time;

import com.allo.restaurant.menu.ports.outbound.TimeProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeProviderImpl implements TimeProvider {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
