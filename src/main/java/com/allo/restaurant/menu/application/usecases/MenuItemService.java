package com.allo.restaurant.menu.application.usecases;

import com.allo.restaurant.menu.domain.MenuItem;
import com.allo.restaurant.menu.domain.PaginatedResult;
import com.allo.restaurant.menu.domain.PaginationRequest;
import com.allo.restaurant.menu.ports.inbound.MenuItemUseCase;
import com.allo.restaurant.menu.ports.outbound.MenuItemPersistence;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService implements MenuItemUseCase {

    private final MenuItemPersistence menuItemPersistence;

    public MenuItemService(MenuItemPersistence menuItemPersistence) {
        this.menuItemPersistence = menuItemPersistence;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemPersistence.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(MenuItem menuItem) {
        return menuItemPersistence.update(menuItem);
    }

    @Override
    public void deleteMenuItem(String id) {
        menuItemPersistence.delete(id);
    }

    @Override
    public MenuItem getMenuItem(String id) {
        return menuItemPersistence.findById(id);
    }

    @Override
    public PaginatedResult getMenuItems(PaginationRequest paginationRequest) {
        return menuItemPersistence.getMenuItems(paginationRequest);
    }
}
