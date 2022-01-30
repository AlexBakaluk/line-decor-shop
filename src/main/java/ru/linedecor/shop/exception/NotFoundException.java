package ru.linedecor.shop.exception;

public class NotFoundException extends RuntimeException {

    private final static String MESSAGE_SUFFIX = " not exists!";

    public NotFoundException(String message) {
        super(message + MESSAGE_SUFFIX);
    }
}
