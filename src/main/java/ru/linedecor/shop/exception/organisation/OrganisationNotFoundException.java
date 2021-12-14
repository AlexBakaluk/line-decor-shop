package ru.linedecor.shop.exception.organisation;

public class OrganisationNotFoundException extends RuntimeException {

    public static final String MESSAGE_PREFIX = "Organisation with ";
    public static final String MESSAGE_SUFFIX = " not exists!";

    public OrganisationNotFoundException(Long id) {
        super(MESSAGE_PREFIX + "id: " + id + MESSAGE_SUFFIX);
    }
}
