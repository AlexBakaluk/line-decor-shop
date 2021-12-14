package ru.linedecor.shop.exception.product.price;

public class PriceTypesTableIsEmptyException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Price types table has no data";

    public PriceTypesTableIsEmptyException() {
        this(DEFAULT_MESSAGE);
    }

    public PriceTypesTableIsEmptyException(String message) {
        super(message);
    }
}
