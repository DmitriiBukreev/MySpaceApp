package com.popovich.myspace.controller.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popovich.myspace.service.exception.EmptyInputStringException;
import com.popovich.myspace.service.exception.NoSuchObjectException;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.NameAlreadyBoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(value = { EmptyInputStringException.class })
    @SneakyThrows
    protected ResponseEntity<Object> handleConflict(EmptyInputStringException ex, WebRequest request) {
        String bodyOfResponse = mapper.writeValueAsString(new Error("Empty string"));
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {NameAlreadyBoundException.class})
    @SneakyThrows
    protected ResponseEntity<Object> handleConflict(NameAlreadyBoundException ex, WebRequest request) {
        String bodyOfResponse = mapper.writeValueAsString(new Error("This name is already taken"));
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.IM_USED, request);
    }

    @ExceptionHandler(value = {NoSuchObjectException.class})
    @SneakyThrows
    protected ResponseEntity<Object> handleConflict(NoSuchObjectException ex, WebRequest request) {
        String bodyOfResponse = mapper.writeValueAsString(new Error("No such object"));
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
