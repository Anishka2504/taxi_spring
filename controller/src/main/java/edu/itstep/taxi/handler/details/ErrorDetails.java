package edu.itstep.taxi.handler.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;
    private HttpStatus httpStatus;
    private int code;
}
