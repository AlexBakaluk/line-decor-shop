package ru.linedecor.shop.exception.product.brand;

public class ProductBrandNotFoundException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Brand with ";
    private static final String MESSAGE_SUFFIX = " not exists!";

    public ProductBrandNotFoundException(int id) {
        super(MESSAGE_PREFIX + "id: " + id + MESSAGE_SUFFIX);
    }

    public ProductBrandNotFoundException(String name) {
        super(MESSAGE_PREFIX + "name: " + name + MESSAGE_SUFFIX);
    }
}
