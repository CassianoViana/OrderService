package com.allo.restaurant.menu.ports.inbound;


import com.allo.restaurant.menu.domain.MenuItem;

public interface MenuItemUseCase {
    MenuItem createMenuItem(MenuItem menuItem);
}
