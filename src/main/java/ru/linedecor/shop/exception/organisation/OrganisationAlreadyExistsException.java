package ru.linedecor.shop.exception.organisation;

public class OrganisationAlreadyExistsException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Organisation already exists!";
    public OrganisationAlreadyExistsException() {
        super(EXCEPTION_MESSAGE);
    }
}
