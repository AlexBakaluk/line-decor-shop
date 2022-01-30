package ru.linedecor.shop.exception;

public class AlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_SUFFIX = " already exists!";

    public AlreadyExistsException(String message) {
        super(message);
    }
}
