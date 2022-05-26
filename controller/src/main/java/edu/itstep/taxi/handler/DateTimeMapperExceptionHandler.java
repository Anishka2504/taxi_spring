package edu.itstep.taxi.handler;

import edu.itstep.taxi.handler.details.ErrorDetails;
import edu.itstep.taxi.service.exception.DateTimeMapperException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Date;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestControllerAdvice
public class DateTimeMapperExceptionHandler {

    @ResponseStatus(NOT_ACCEPTABLE)
    @ExceptionHandler(DateTimeMapperException.class)
    public ErrorDetails handleDateTimeMapperException(DateTimeMapperException exception) {
        return ErrorDetails.builder()
                .timestamp(Date.from(Instant.now()))
                .message(exception.getMessage())
                .details("Incorrect input for data field. Check your input and try again.")
                .httpStatus(NOT_ACCEPTABLE)
                .code(NOT_ACCEPTABLE.value())
                .build();
    }
}
