package com.allo.restaurant.menu.adapters.inbound.rest;

import com.allo.restaurant.menu.adapters.inbound.rest.contracts.MenuItemRequest;
import com.allo.restaurant.menu.adapters.inbound.rest.contracts.MenuItemResponse;
import com.allo.restaurant.menu.adapters.inbound.rest.contracts.PaginatedMenuItemResponse;
import com.allo.restaurant.menu.adapters.inbound.rest.mappers.MenuItemContractMapper;
import com.allo.restaurant.menu.domain.MenuItem;
import com.allo.restaurant.menu.domain.PaginatedResult;
import com.allo.restaurant.menu.domain.PaginationRequest;
import com.allo.restaurant.menu.ports.inbound.MenuItemUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {

    private final MenuItemUseCase menuItemUseCase;
    private final MenuItemContractMapper menuItemContractMapper;

    public MenuItemController(MenuItemUseCase menuItemUseCase, MenuItemContractMapper menuItemContractMapper) {
        this.menuItemUseCase = menuItemUseCase;
        this.menuItemContractMapper = menuItemContractMapper;
    }

    @PostMapping("/menu-items")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItemResponse createMenuItem(@RequestBody @Valid MenuItemRequest request) {
        var domain = menuItemContractMapper.fromRequestToDomain(request);
        MenuItem createdMenuItem = menuItemUseCase.createMenuItem(domain);
        return menuItemContractMapper.fromDomainToResponse(createdMenuItem);
    }

    @GetMapping("/menu-items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuItemResponse getMenuItem(@PathVariable String id) {
        MenuItem menuItem = menuItemUseCase.getMenuItem(id);
        return menuItemContractMapper.fromDomainToResponse(menuItem);
    }

    @GetMapping("/menu-items")
    @ResponseStatus(HttpStatus.OK)
    public PaginatedMenuItemResponse getMenuItems(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int size) {
        PaginationRequest paginationRequest = PaginationRequest.of(offset, size);
        PaginatedResult result = menuItemUseCase.getMenuItems(paginationRequest);
        List<MenuItemResponse> items = result.items().stream()
                .map(menuItemContractMapper::fromDomainToResponse)
                .toList();
        
        return new PaginatedMenuItemResponse(items, result.totalRecords());
    }

    @PutMapping("/menu-items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MenuItemResponse updateMenuItem(@RequestBody @Valid MenuItemRequest request, @PathVariable String id) {
        var domain = menuItemContractMapper.fromRequestToDomain(request);
        MenuItem createdMenuItem = menuItemUseCase.updateMenuItem(domain.withId(id));
        return menuItemContractMapper.fromDomainToResponse(createdMenuItem);
    }

    @DeleteMapping("/menu-items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenuItem(@PathVariable String id) {
        menuItemUseCase.deleteMenuItem(id);
    }
}
