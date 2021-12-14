package ru.linedecor.shop.exception.product.price;

public class PriceTypeAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Price type with ";
    private static final String MESSAGE_SUFFIX = " already exists!";

    public PriceTypeAlreadyExistsException(String typeName) {
        super(MESSAGE_PREFIX + " name: " + typeName + MESSAGE_SUFFIX);
    }
}
