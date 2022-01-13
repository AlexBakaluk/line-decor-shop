package ru.linedecor.shop.exception.product.brand;

public class ProductBrandAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Brand with ";
    private static final String MESSAGE_SUFFIX = " already exists!";

    public ProductBrandAlreadyExistsException(String existedBrandName) {
        super(MESSAGE_PREFIX + "name: " + existedBrandName + MESSAGE_SUFFIX);
    }
}
