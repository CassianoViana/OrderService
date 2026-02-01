package com.allo.restaurant.menu.application.usecases;

import com.allo.restaurant.menu.domain.MenuItem;
import com.allo.restaurant.menu.ports.inbound.MenuItemUseCase;
import com.allo.restaurant.menu.ports.outbound.MenuItemRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuItemService implements MenuItemUseCase {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
}
