package edu.itstep.taxi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class IncorrectValueException extends RuntimeException{

    public IncorrectValueException(String message) {
        super(message);
    }

    public IncorrectValueException() {
    }
}
