package ru.linedecor.shop.handler.product.price;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.linedecor.shop.exception.ExceptionMessage;
import ru.linedecor.shop.exception.product.price.PriceTypesTableIsEmptyException;

@RestControllerAdvice
public class PriceTypesTableIsEmptyExceptionHandler {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    @ExceptionHandler(value = PriceTypesTableIsEmptyException.class)
    protected ExceptionMessage handlePriceTypesTableIsEmptyException(Exception e) {
        return new ExceptionMessage(e.getMessage());
    }

}
