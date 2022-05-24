package edu.itstep.taxi.service.converter.uses;

import edu.itstep.taxi.service.exception.DateTimeMapperException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static edu.itstep.taxi.service.constant.Constant.DATE_PATTERN;
import static edu.itstep.taxi.service.constant.Constant.DATE_TIME_PATTERN;

public class DateTimeMapper {

    public static String date(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(DATE_TIME_PATTERN).format(date);
    }

    public static String localDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static Date date(String date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(DATE_TIME_PATTERN).parse(date);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        throw new DateTimeMapperException();
    }

    public static LocalDate localDate(String localDate) {
        if (localDate == null) {
            return null;
        }
        try {
            return LocalDate.parse(localDate, DateTimeFormatter.ofPattern(DATE_PATTERN));
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
        }
        throw new DateTimeMapperException();
    }
}