package com.allo.restaurant.order.adapters.inbound.rest.mappers;

import com.allo.restaurant.order.adapters.inbound.rest.contracts.MenuItemRequest;
import com.allo.restaurant.order.adapters.inbound.rest.contracts.MenuItemResponse;
import com.allo.restaurant.order.domain.MenuItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuItemContractMapper {

    MenuItem fromRequestToDomain(MenuItemRequest request);

    MenuItemResponse fromDomainToResponse(MenuItem createdMenuItem);
}
