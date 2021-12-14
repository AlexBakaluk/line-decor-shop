package ru.linedecor.shop.handler.product.price;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.linedecor.shop.exception.ExceptionMessage;
import ru.linedecor.shop.exception.product.price.PriceTypeAlreadyExistsException;

@RestControllerAdvice
public class PriceTypeAlreadyExistsExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = PriceTypeAlreadyExistsException.class)
    protected ExceptionMessage handlePriceTypeAlreadyExistsException(Exception e) {
        return new ExceptionMessage(e.getMessage());
    }

}
