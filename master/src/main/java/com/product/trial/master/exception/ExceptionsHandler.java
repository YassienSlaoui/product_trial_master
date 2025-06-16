package com.product.trial.master.exception;

import org.springframework.http.HttpStatus;

public class ExceptionsHandler extends Exception {

    private static final long serialVersionUID = -3545472189300959311L;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR ;
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
    public ExceptionsHandler() {
    }

    public ExceptionsHandler(String message) {
        super(message);
    }

    public ExceptionsHandler(Throwable cause) {
        super(cause);
    }

    public ExceptionsHandler(String message, Throwable cause) {
        super(message, cause);
    }
    public ExceptionsHandler(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
