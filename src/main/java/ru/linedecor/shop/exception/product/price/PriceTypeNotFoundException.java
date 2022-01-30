package ru.linedecor.shop.exception.product.price;

import ru.linedecor.shop.exception.NotFoundException;

public class PriceTypeNotFoundException extends NotFoundException {

    private static final String MESSAGE_PREFIX = "Price type with ";

    public PriceTypeNotFoundException(int id) {
        super(MESSAGE_PREFIX + "id: " + id);
    }
}
