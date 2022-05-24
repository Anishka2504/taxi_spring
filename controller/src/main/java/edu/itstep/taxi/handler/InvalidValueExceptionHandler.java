package edu.itstep.taxi.handler;

import edu.itstep.taxi.handler.details.ErrorDetails;
import edu.itstep.taxi.service.exception.IncorrectValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Date;
import java.time.Instant;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestControllerAdvice
public class InvalidValueExceptionHandler {

    @ExceptionHandler(IncorrectValueException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public ErrorDetails handleInvalidValueException(IncorrectValueException exception) {
        return ErrorDetails.builder()
                .timestamp(Date.from(Instant.now()))
                .message(exception.getMessage())
                .details("Incorrect value!")
                .httpStatus(NOT_ACCEPTABLE)
                .code(NOT_ACCEPTABLE.value())
                .build();
    }

}
