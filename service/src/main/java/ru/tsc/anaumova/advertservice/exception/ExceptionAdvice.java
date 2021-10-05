package ru.tsc.anaumova.advertservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseBody
    public ResponseEntity<String> handleEntityNotFoundException() {
        return new ResponseEntity<>("Record not found...", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IncorrectPasswordException.class})
    @ResponseBody
    public ResponseEntity<String> handleIncorrectPasswordException() {
        return new ResponseEntity<>("Incorrect old password entered...", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({IncorrectStatusException.class})
    @ResponseBody
    public ResponseEntity<String> handleIncorrectStatusException() {
        return new ResponseEntity<>("Incorrect sort. Value not found...", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ExistUsernameException.class})
    @ResponseBody
    public ResponseEntity<String> handleExistUsernameException() {
        return new ResponseEntity<>("Username already exists...", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}