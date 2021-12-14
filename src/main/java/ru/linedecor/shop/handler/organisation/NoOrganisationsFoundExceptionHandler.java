package ru.linedecor.shop.handler.organisation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.linedecor.shop.NoOrganisationsFoundException;
import ru.linedecor.shop.exception.ExceptionMessage;

@RestControllerAdvice
public class NoOrganisationsFoundExceptionHandler {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @ExceptionHandler(value = {NoOrganisationsFoundException.class})
    protected ExceptionMessage handleNoOrganisationsFoundException(Exception exception) {
        return new ExceptionMessage(exception.getMessage());
    }

}
