package com.allo.restaurant.menu.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String menuItemNotFound) {
        super(menuItemNotFound);
    }
}
