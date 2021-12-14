package ru.linedecor.shop.handler.organisation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.linedecor.shop.exception.ExceptionMessage;
import ru.linedecor.shop.exception.organisation.OrganisationNotFoundException;

@RestControllerAdvice
public class OrganisationNotFoundExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = {OrganisationNotFoundException.class})
    protected ExceptionMessage handleOrganisationNotFoundException(OrganisationNotFoundException exception) {
        return new ExceptionMessage(exception.getMessage());
    }

}
