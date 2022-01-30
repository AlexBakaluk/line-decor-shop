package ru.linedecor.shop.exception.product.category;

public class CategoryNotFoundException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Category with ";
    private static final String MESSAGE_SUFFIX = " not exists!";

    public CategoryNotFoundException(int id) {
        super(MESSAGE_PREFIX + " id: " + id + MESSAGE_SUFFIX);
    }
}
