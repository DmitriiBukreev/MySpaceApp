package com.popovich.myspace.controller.exception;

import com.popovich.myspace.service.exception.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NoSuchMasterException.class)
    public String handler(NoSuchMasterException e, Model model) {
        model.addAttribute("exception", e);
        return "errors/nomaster";
    }

    @ExceptionHandler(NameIsAlreadyTakenException.class)
    public String handler(NameIsAlreadyTakenException e, Model model) {
        model.addAttribute("exception", e);
        return "errors/nametaken";
    }

    @ExceptionHandler(InvalidAgeException.class)
    public String handler(InvalidAgeException e, Model model) {
        model.addAttribute("exception", e);
        return "errors/invalidage";
    }

    @ExceptionHandler(InvalidNameException.class)
    public String handler(InvalidNameException e, Model model) {
        model.addAttribute("exception", e);
        return "errors/invalidname";
    }
}

