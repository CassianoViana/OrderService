package com.allo.restaurant.menu.ports.outbound;

import com.allo.restaurant.menu.domain.MenuItem;
import com.allo.restaurant.menu.domain.PaginatedResult;
import com.allo.restaurant.menu.domain.PaginationRequest;

public interface MenuItemPersistence {
    MenuItem save(MenuItem menuItem);

    MenuItem update(MenuItem menuItem);

    void delete(String id);

    MenuItem findById(String id);

    PaginatedResult getMenuItems(PaginationRequest paginationRequest);
}
