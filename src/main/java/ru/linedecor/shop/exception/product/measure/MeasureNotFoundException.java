package ru.linedecor.shop.exception.product.measure;

import ru.linedecor.shop.exception.NotFoundException;

public class MeasureNotFoundException extends NotFoundException {

    private static final String MESSAGE_PREFIX = "Measure with ";

    public MeasureNotFoundException(int id) {
        super(MESSAGE_PREFIX + "id: " + id);
    }

}
