package com.wrapsody.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class WrapsodyCommunicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WrapsodyNotFoundException.class)
    public final ResponseEntity<WrapsodyCommunicationError> handleNotFoundException(Exception ex, WebRequest request) {
        WrapsodyCommunicationError error = new WrapsodyCommunicationError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrapsodyUnauthorizedException.class)
    public final ResponseEntity<WrapsodyCommunicationError> handleUnauthorizedException(Exception ex, WebRequest request) {
        WrapsodyCommunicationError error = new WrapsodyCommunicationError(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
