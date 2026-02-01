package com.allo.restaurant.menu.adapters.outbound.persistence.mongodb;

import com.allo.restaurant.menu.adapters.outbound.persistence.mongodb.model.MenuItemModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoMenuItemRepository extends MongoRepository<MenuItemModel, String> {
}
