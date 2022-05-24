package edu.itstep.taxi.handler;

import edu.itstep.taxi.handler.details.ErrorDetails;
import edu.itstep.taxi.service.exception.DataNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Date;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class DataNotFoundExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public ErrorDetails handleEntityNotFoundException(DataNotFoundException exception) {
        return ErrorDetails.builder()
                .timestamp(Date.from(Instant.now()))
                .message(exception.getMessage())
                .details("No items are found")
                .httpStatus(NOT_FOUND)
                .code(NOT_FOUND.value())
                .build();
    }
}
