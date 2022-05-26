package edu.itstep.taxi.service.validator.impl;

import edu.itstep.taxi.repository.CarRepository;
import edu.itstep.taxi.repository.DriverRepository;
import edu.itstep.taxi.service.exception.DataNotFoundException;
import edu.itstep.taxi.service.exception.IncorrectValueException;
import edu.itstep.taxi.service.validator.DataValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataValidatorImpl implements DataValidator {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    public DataValidatorImpl(CarRepository carRepository, DriverRepository driverRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    //-----validations------------------------------------------------------------------------------------------------------

    @Override
    public void validateCarId(Long id) {
        if (id <= 0) {
            throw new IncorrectValueException("ID value can't be less than or equal to zero");
        } else if (!carRepository.existsById(id)) {
            throw new DataNotFoundException("Car is not found by ID " + id);
        }
    }

    @Override
    public void validateDriverId(Long id) {
        if (id <= 0) {
            throw new IncorrectValueException("ID value can't be less than or equals to zero");
        }
        if (!driverRepository.existsById(id)) {
            throw new DataNotFoundException("Driver with ID " + id + " isn't exists!");
        }
    }

    @Override
    public void validateBothId(Long carId, Long driverId) {
        if (carId <= 0 || driverId <= 0) {
            throw new IncorrectValueException("ID can't be less then or equal to zero");
        } else if (!carRepository.existsById(carId)) {
            throw new DataNotFoundException("Car is not found by ID " + carId);
        } else if (!driverRepository.existsById(driverId)) {
            throw new DataNotFoundException("Driver is not found by ID " + driverId);
        }
    }

    @Override
    public void validateResult(List<?> result) {
        if (result.isEmpty()) {
            throw new DataNotFoundException("No data found!");
        }
    }

    @Override
    public void validateNumberParameter(Integer... number) {
        for (Integer i : number) {
            if (i <= 0) {
                throw new IncorrectValueException
                        ("Incorrect value " + i + ". Check your input and try again");
            }
        }
    }

    @Override
    public void validateStringParameter(String... line) {
        for (String s : line) {
            if (isEmpty(s) || isBlank(s)) {
                throw new IncorrectValueException
                        ("Incorrect value " + s + ". Check your input and try again");
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
    private boolean isEmpty(String line) {
        return line.length() == 0;
    }

    private boolean isBlank(String line) {
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}
