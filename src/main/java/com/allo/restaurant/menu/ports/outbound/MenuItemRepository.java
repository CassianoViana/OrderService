package com.allo.restaurant.menu.ports.outbound;

import com.allo.restaurant.menu.domain.MenuItem;

public interface MenuItemRepository {
    MenuItem save(MenuItem menuItem);
}
