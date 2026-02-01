package com.allo.restaurant.menu.adapters.outbound.persistence.mongodb;

import com.allo.restaurant.menu.adapters.outbound.persistence.mappers.MenuItemModelMapper;
import com.allo.restaurant.menu.domain.MenuItem;
import com.allo.restaurant.menu.ports.outbound.MenuItemRepository;
import com.allo.restaurant.menu.ports.outbound.TimeProvider;
import com.allo.restaurant.menu.ports.outbound.UuidProvider;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoMenuItemRepository implements MenuItemRepository {

    private final MongoTemplate mongoTemplate;
    private final MenuItemModelMapper mapper;
    private final TimeProvider timeProvider;
    private final UuidProvider uuidProvider;

    public MongoMenuItemRepository(
            MongoTemplate mongoTemplate,
            MenuItemModelMapper mapper,
            TimeProvider timeProvider,
            UuidProvider uuidProvider) {
        this.mongoTemplate = mongoTemplate;
        this.mapper = mapper;
        this.timeProvider = timeProvider;
        this.uuidProvider = uuidProvider;
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        var model = mapper.toModel(menuItem.withCreatedAt(timeProvider.now())
                .withId(uuidProvider.generateId()));
        return mapper.fromModelToDomain(mongoTemplate.save(model));
    }
}
