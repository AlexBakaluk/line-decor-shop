package ru.linedecor.shop.exception.product.characteristic;

public class CharacteristicGroupAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_PREFIX = "Characteristic group with ";
    private static final String MESSAGE_SUFFIX = " already exists!";

    public CharacteristicGroupAlreadyExistsException(String groupName) {
        super(MESSAGE_PREFIX + "name: " + groupName + MESSAGE_SUFFIX);
    }

}
