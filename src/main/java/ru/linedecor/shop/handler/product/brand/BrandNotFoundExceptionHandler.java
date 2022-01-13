package ru.linedecor.shop.handler.product.brand;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.linedecor.shop.exception.ExceptionMessage;
import ru.linedecor.shop.exception.product.brand.ProductBrandNotFoundException;

@RestControllerAdvice
public class BrandNotFoundExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = ProductBrandNotFoundException.class)
    protected ExceptionMessage handleBrandNotFoundException(Exception e) {
        return new ExceptionMessage(e.getMessage());
    }

}
