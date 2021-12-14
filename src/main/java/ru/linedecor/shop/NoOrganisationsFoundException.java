package ru.linedecor.shop;

public class NoOrganisationsFoundException extends RuntimeException {

    public NoOrganisationsFoundException() {
        super("No data in organisation table!");
    }
}
