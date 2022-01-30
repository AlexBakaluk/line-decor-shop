package ru.linedecor.shop.exception.product.category;

public class CategoryAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Category with ";
    private static final String MESSAGE_SUFFIX = " already exists!";

    public CategoryAlreadyExistsException(String categoryName) {
        super(MESSAGE_PREFIX + "name: " + categoryName + MESSAGE_SUFFIX);
    }
}
