package com.allo.restaurant.menu.ports.outbound.id;

import com.allo.restaurant.menu.ports.outbound.UuidProvider;

import java.util.UUID;

public class UuidProviderImpl implements UuidProvider {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
