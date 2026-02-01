package com.allo.restaurant.menu.adapters.inbound.rest;

import com.allo.restaurant.menu.adapters.inbound.rest.contracts.MenuItemRequest;
import com.allo.restaurant.menu.adapters.inbound.rest.contracts.MenuItemResponse;
import com.allo.restaurant.menu.adapters.inbound.rest.mappers.MenuItemContractMapper;
import com.allo.restaurant.menu.domain.MenuItem;
import com.allo.restaurant.menu.ports.inbound.MenuItemUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuItemController {

    private final MenuItemUseCase menuItemUseCase;
    private final MenuItemContractMapper menuItemContractMapper;

    public MenuItemController(MenuItemUseCase menuItemUseCase, MenuItemContractMapper menuItemContractMapper) {
        this.menuItemUseCase = menuItemUseCase;
        this.menuItemContractMapper = menuItemContractMapper;
    }

    @PostMapping("/menu-items")
    public MenuItemResponse createMenuItem(@RequestBody MenuItemRequest request) {
        var domain = menuItemContractMapper.fromRequestToDomain(request);
        MenuItem createdMenuItem = menuItemUseCase.createMenuItem(domain);
        return menuItemContractMapper.fromDomainToResponse(createdMenuItem);
    }

}
