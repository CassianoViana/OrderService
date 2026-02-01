package com.allo.restaurant.menu.ports.outbound.id;

import com.allo.restaurant.menu.ports.outbound.UuidProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidProviderImpl implements UuidProvider {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
