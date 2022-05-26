package edu.itstep.taxi.service.validator;

import java.util.List;

public interface DataValidator {

    void validateCarId(Long id);

    void validateDriverId(Long id);

    void validateBothId(Long carId, Long driverId);

    void validateResult(List<?> result);

    void validateNumberParameter(Integer... number);

    void validateStringParameter(String... line);
}
