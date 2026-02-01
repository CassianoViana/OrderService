package com.allo.restaurant.menu.adapters.outbound.persistence.mappers;

import com.allo.restaurant.menu.adapters.outbound.persistence.mongodb.model.MenuItemModel;
import com.allo.restaurant.menu.domain.MenuItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuItemModelMapper {

    MenuItemModel toModel(MenuItem domain);

    MenuItem fromModelToDomain(MenuItemModel save);
}
