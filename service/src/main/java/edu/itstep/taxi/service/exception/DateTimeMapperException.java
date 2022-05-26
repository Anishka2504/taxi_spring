package edu.itstep.taxi.service.exception;

public class DateTimeMapperException extends RuntimeException {

    public DateTimeMapperException(String message) {
        super(message);
    }

    public DateTimeMapperException() {
        this("Incorrect date-time source string!");
    }
}
