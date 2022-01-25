package ru.linedecor.shop.exception.product.characteristic;

public class CharacteristicNotFoundException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Characteristic with ";
    private static final String MESSAGE_SUFFIX = " not exists!";

    public CharacteristicNotFoundException(long id) {
        super(MESSAGE_PREFIX + " id: " + id + MESSAGE_SUFFIX);
    }
}
