package ru.linedecor.shop.handler.organisation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.linedecor.shop.exception.ExceptionMessage;
import ru.linedecor.shop.exception.organisation.OrganisationAlreadyExistsException;

@RestControllerAdvice
public class OrganisationAlreadyExistsExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {OrganisationAlreadyExistsException.class})
    protected ExceptionMessage handleOrganisationAlreadyExistsException(OrganisationAlreadyExistsException exception) {
        return new ExceptionMessage(exception.getMessage());
    }

}
