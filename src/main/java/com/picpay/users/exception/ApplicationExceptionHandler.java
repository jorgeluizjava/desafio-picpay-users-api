package com.picpay.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ApplicationErrorDetail handleApplicationFieldError(Exception exception) {

        MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
        BindingResult bindingResult = ex.getBindingResult();
        String message = bindingResult
                                .getFieldErrors()
                                .stream()
                                .map(fieldError -> {
                                    return fieldError.getField() + " - " + fieldError.getDefaultMessage();
                                 })
                                .collect(Collectors.joining(", "));

        return new ApplicationErrorDetail("422", message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ApplicationErrorDetail handleEntityNotFoundException(Exception exception) {
        return new ApplicationErrorDetail("404", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {TransactionNotAuthorizedException.class})
    public ApplicationErrorDetail handleTransactionNotAuthorizedException(Exception exception) {
        return new ApplicationErrorDetail("401", exception.getMessage());
    }

}
