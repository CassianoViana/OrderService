package com.allo.restaurant.menu.adapters.outbound.persistence.mongodb;

import com.allo.restaurant.menu.adapters.outbound.persistence.mappers.MenuItemModelMapper;
import com.allo.restaurant.menu.adapters.outbound.persistence.mongodb.model.MenuItemModel;
import com.allo.restaurant.menu.domain.MenuItem;
import com.allo.restaurant.menu.domain.PaginatedResult;
import com.allo.restaurant.menu.domain.PaginationRequest;
import com.allo.restaurant.menu.exceptions.NotFoundException;
import com.allo.restaurant.menu.ports.outbound.MenuItemPersistence;
import com.allo.restaurant.menu.ports.outbound.TimeProvider;
import com.allo.restaurant.menu.ports.outbound.UuidProvider;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoMenuItemPersistence implements MenuItemPersistence {

    private final MongoMenuItemRepository mongoMenuItemRepository;
    private final MongoTemplate mongoTemplate;
    private final MenuItemModelMapper mapper;
    private final TimeProvider timeProvider;
    private final UuidProvider uuidProvider;

    public MongoMenuItemPersistence(
            MongoMenuItemRepository mongoMenuItemRepository, MongoTemplate mongoTemplate,
            MenuItemModelMapper mapper,
            TimeProvider timeProvider,
            UuidProvider uuidProvider) {
        this.mongoMenuItemRepository = mongoMenuItemRepository;
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

    @Override
    public MenuItem update(MenuItem menuItem) throws NotFoundException {
        var model = mongoTemplate.findById(menuItem.id(), MenuItemModel.class);
        if (model == null) {
            throw new NotFoundException("Menu item not found");
        }
        model = mapper.toModel(menuItem.withUpdatedAt(timeProvider.now())
                .withCreatedAt(model.createdAt())
                .withId(menuItem.id()));
        return mapper.fromModelToDomain(mongoTemplate.save(model));
    }

    @Override
    public void delete(String id) {
        mongoMenuItemRepository.deleteById(id);
    }

    @Override
    public MenuItem findById(String id) {
        return mongoMenuItemRepository.findById(id)
                .map(mapper::fromModelToDomain)
                .orElseThrow(() -> new NotFoundException("Menu item not found"));
    }

    @Override
    public PaginatedResult getMenuItems(PaginationRequest paginationRequest) {
        List<MenuItemModel> allItems = mongoMenuItemRepository.findAll();
        long totalRecords = allItems.size();
        
        List<MenuItem> paginatedItems = allItems.stream()
                .skip(paginationRequest.offset())
                .limit(paginationRequest.size())
                .map(mapper::fromModelToDomain)
                .toList();
        
        return new PaginatedResult(paginatedItems, totalRecords);
    }

}
