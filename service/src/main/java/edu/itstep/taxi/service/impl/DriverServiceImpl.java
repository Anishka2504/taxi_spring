package edu.itstep.taxi.service.impl;

import edu.itstep.taxi.entity.Car;
import edu.itstep.taxi.entity.Driver;
import edu.itstep.taxi.repository.CarRepository;
import edu.itstep.taxi.repository.DriverRepository;
import edu.itstep.taxi.service.DriverService;
import edu.itstep.taxi.service.converter.DriverConverter;
import edu.itstep.taxi.service.converter.uses.DateTimeMapper;
import edu.itstep.taxi.service.dto.DriverDto;
import edu.itstep.taxi.service.dto.creation.DriverCreationDto;
import edu.itstep.taxi.service.exception.DataNotFoundException;
import edu.itstep.taxi.service.validator.DataValidator;
import edu.itstep.taxi.service.validator.impl.DataValidatorImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final DriverConverter driverConverter;
    private final DataValidator validator;


    public DriverServiceImpl(CarRepository carRepository, DriverRepository driverRepository,
                             DriverConverter driverConverter, DataValidatorImpl validator) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
        this.driverConverter = driverConverter;
        this.validator = validator;
    }

    //-----operations-------------------------------------------------------------------------------------------------------

    @Override
    public DriverCreationDto addNewDriver(DriverCreationDto driverCreationDto) {
        validator.validateStringParameter
                (driverCreationDto.getLastName(),
                        driverCreationDto.getName(),
                        driverCreationDto.getMiddleName(),
                        driverCreationDto.getDriverLicence()
                );
        return driverConverter.convertDriverToDriverCreationDto
                (driverRepository.save(driverConverter.convertDriverCreationDtoToDriver(driverCreationDto)));
    }


    @Override
    public List<DriverDto> getAll(Integer pageNumber, Integer pageSize) {
        List<DriverDto> result = new ArrayList<>(0);
        for (Driver driver : driverRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent()) {
            result.add(driverConverter.convertDriverToDriverDto(driver));
        }
        validator.validateResult(result);
        return result;
    }

    @Override
    public DriverDto findOneById(long id) {
        validator.validateDriverId(id);
        return driverConverter.convertDriverToDriverDto(driverRepository.findDriverById(id));
    }

    @Override
    public List<DriverDto> findAllByLastName(String lastName, Integer pageNumber, Integer pageSize) {
        List<DriverDto> result = new ArrayList<>(0);
        for (Driver driver : driverRepository.findAllByLastNameContains
                (lastName, PageRequest.of(pageNumber, pageSize, Sort.by("dateOfBirth").descending())).getContent()) {
            result.add(driverConverter.convertDriverToDriverDto(driver));
        }
        validator.validateResult(result);
        return result;
    }

    @Override
    public List<DriverDto> findAllByAge(int age, Integer pageNumber, Integer pageSize) {
        List<DriverDto> result = new ArrayList<>(0);
        for (Driver driver : driverRepository.findAll
                (PageRequest.of(pageNumber, pageSize, Sort.by("lastName").ascending())).getContent()) {
            if (Period.between(driver.getDateOfBirth(), LocalDate.now()).getYears() == age) {
                result.add(driverConverter.convertDriverToDriverDto(driver));
            }
        }
        validator.validateResult(result);
        return result;
    }

    @Override
    public DriverDto findDriverByDriverLicence(String driverLicence) {
        if (driverRepository.findByDriverLicence(driverLicence) == null) {
            throw new DataNotFoundException("Driver is not found!");
        }
        return driverConverter.convertDriverToDriverDto(driverRepository.findByDriverLicence(driverLicence));
    }


    @Override
    public DriverDto changeLastName(Long id, String lastName) {
        validator.validateDriverId(id);
        validator.validateStringParameter(lastName);
        DriverDto driverDto = driverConverter.convertDriverToDriverDto(driverRepository.findDriverById(id));
        driverDto.setLastName(lastName);
        driverDto.setLastModified(DateTimeMapper.date(new Date()));
        return driverConverter.convertDriverToDriverDto
                (driverRepository.save(driverConverter.convertDriverDtoToDriver(driverDto)));
    }

    @Override
    public DriverDto changeName(Long id, String name) {
        validator.validateDriverId(id);
        validator.validateStringParameter(name);
        DriverDto driverDto = driverConverter.convertDriverToDriverDto(driverRepository.findDriverById(id));
        driverDto.setName(name);
        driverDto.setLastModified(DateTimeMapper.date(new Date()));
        return driverConverter.convertDriverToDriverDto
                (driverRepository.save(driverConverter.convertDriverDtoToDriver(driverDto)));
    }

    @Override
    public DriverDto changeDriverLicence(Long id, String licence) {
        validator.validateDriverId(id);
        validator.validateStringParameter(licence);
        DriverDto driverDto = driverConverter.convertDriverToDriverDto(driverRepository.findDriverById(id));
        driverDto.setDriverLicence(licence);
        driverDto.setLastModified(DateTimeMapper.date(new Date()));
        return driverConverter.convertDriverToDriverDto
                (driverRepository.save(driverConverter.convertDriverDtoToDriver(driverDto)));
    }

    @Override
    public void deleteById(long id) {
        validator.validateDriverId(id);
        for (Car car : carRepository.findAll()) {
            car.getDrivers().removeIf(driver -> driver.getId() == id);
        }
        driverRepository.deleteById(id);
    }


}


