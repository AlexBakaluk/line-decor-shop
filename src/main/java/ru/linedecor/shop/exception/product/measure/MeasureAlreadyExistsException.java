package ru.linedecor.shop.exception.product.measure;

import ru.linedecor.shop.exception.AlreadyExistsException;

public class MeasureAlreadyExistsException extends AlreadyExistsException {

    private static final String MESSAGE_PREFIX = "Measure with ";

    public MeasureAlreadyExistsException(String measureName) {
        super(MESSAGE_PREFIX + "name: [" + measureName + "]");
    }
}
