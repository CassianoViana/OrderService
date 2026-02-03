package com.allo.restaurant.order.ports.outbound;


import com.allo.restaurant.order.domain.MenuItem;

public interface MenuItemUseCase {

    MenuItem getMenuItem(String id);

}
