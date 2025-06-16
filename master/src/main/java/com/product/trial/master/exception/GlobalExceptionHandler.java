package com.product.trial.master.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExceptionsHandler.class)
    public ResponseEntity<String> handleBadRequest(ExceptionsHandler ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleBadRequest(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request: " + checkError(ex));
    }
    private String checkError(HttpMessageNotReadableException ex){
        if(ex.getCause() instanceof InvalidFormatException invalidFormatException && invalidFormatException.getTargetType().isEnum()) {
                Object[] allowedValues = invalidFormatException.getTargetType().getEnumConstants();
                return String.format(
                        "Invalid value '%s'. Allowed values for %s are: %s",
                        invalidFormatException.getValue(),
                        invalidFormatException.getTargetType().getSimpleName(),
                        Arrays.toString(allowedValues)
                );
            }

        return ex.getMostSpecificCause().getMessage();
    }
}
