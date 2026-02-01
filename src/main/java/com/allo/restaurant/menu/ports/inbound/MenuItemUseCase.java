package com.allo.restaurant.menu.ports.inbound;


import com.allo.restaurant.menu.domain.MenuItem;
import com.allo.restaurant.menu.domain.PaginatedResult;
import com.allo.restaurant.menu.domain.PaginationRequest;

public interface MenuItemUseCase {
    MenuItem createMenuItem(MenuItem menuItem);

    MenuItem updateMenuItem(MenuItem menuItem);

    void deleteMenuItem(String id);

    MenuItem getMenuItem(String id);

    PaginatedResult getMenuItems(PaginationRequest paginationRequest);
}
