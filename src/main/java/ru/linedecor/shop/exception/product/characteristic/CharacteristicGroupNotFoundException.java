package ru.linedecor.shop.exception.product.characteristic;

public class CharacteristicGroupNotFoundException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Characteristic group with ";
    private static final String MESSAGE_SUFFIX = " not exists!";

    public CharacteristicGroupNotFoundException(int id) {
        super(MESSAGE_PREFIX + "id: " + id + MESSAGE_SUFFIX);
    }
}
