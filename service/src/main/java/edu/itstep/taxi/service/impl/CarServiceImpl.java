package edu.itstep.taxi.service.impl;

import edu.itstep.taxi.entity.Car;
import edu.itstep.taxi.entity.Driver;
import edu.itstep.taxi.repository.CarRepository;
import edu.itstep.taxi.repository.DriverRepository;
import edu.itstep.taxi.service.CarService;
import edu.itstep.taxi.service.converter.CarConverter;
import edu.itstep.taxi.service.converter.DriverConverter;
import edu.itstep.taxi.service.converter.uses.DateTimeMapper;
import edu.itstep.taxi.service.dto.CarDriverDto;
import edu.itstep.taxi.service.dto.CarDto;
import edu.itstep.taxi.service.dto.DriverDto;
import edu.itstep.taxi.service.exception.IncorrectValueException;
import edu.itstep.taxi.service.validator.DataValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;
    private final DriverRepository driverRepository;
    private final DriverConverter driverConverter;
    private final DataValidator validator;

    public CarServiceImpl(CarRepository carRepository, CarConverter carConverter,
                          DriverRepository driverRepository, DriverConverter driverConverter, DataValidator validator) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
        this.driverRepository = driverRepository;
        this.driverConverter = driverConverter;
        this.validator = validator;
    }

    //-----operations---------------------------------------------------------------------------------------------------

    @Override
    public CarDto addNewCar(CarDto carDto) {
        validator.validateStringParameter(carDto.getBrand(), carDto.getModel(), carDto.getEquipment());
        validator.validateNumberParameter((int) carDto.getYear(), carDto.getCost(), (int) carDto.getFuelConsumption());
        carDto.setDateCreation(DateTimeMapper.date(new Date()));
        carDto.setLastModified(DateTimeMapper.date(new Date()));
        return carConverter.convertCarToCarDto(carRepository.save(carConverter.convertCarDtoToCar(carDto)));
    }

    @Override
    public List<CarDriverDto> getAllCars(Integer pageNumber, Integer pageSize) {
        List<CarDriverDto> result = new ArrayList<>(0);
        Page<Car> carPage = carRepository.findAll(PageRequest.of(pageNumber, pageSize));
        for (Car car : carPage.getContent()) {
            result.add(carConverter.convertCarToCarDriverDto(car));
        }
        validator.validateResult(result);
        return result;
    }


    @Override
    public CarDriverDto findCarById(long id) {
        validator.validateCarId(id);
        return carConverter.convertCarToCarDriverDto(carRepository.findCarById(id));
    }

    @Override
    public List<CarDto> findCarByEquipment(String equipment, Integer pageNumber, Integer pageSize) {
        List<CarDto> result = new ArrayList<>(0);
        for (Car car : carRepository.findCarsByEquipment
                (equipment, PageRequest.of(pageNumber, pageSize, Sort.by("year").descending())).getContent()) {
            result.add(carConverter.convertCarToCarDto(car));
        }
        validator.validateResult(result);
        return result;
    }

    @Override
    public List<CarDto> findCarByYear(short year, Integer pageNumber, Integer pageSize) {
        List<CarDto> result = new ArrayList<>(0);
        for (Car car : carRepository.findCarsByYear(year, PageRequest.of(pageNumber, pageSize)).getContent()) {
            result.add(carConverter.convertCarToCarDto(car));
        }
        validator.validateResult(result);
        return result;
    }

    @Override
    public List<CarDto> findCarByDriver(Long driverId, Integer pageNumber, Integer pageSize) {
        validator.validateDriverId(driverId);
        List<CarDto> result = new ArrayList<>(0);
        for (Car car : carRepository.findAll(PageRequest.of(pageNumber, pageSize,
                Sort.by("equipment").and(Sort.by("year").descending()))).getContent()) {
            for (Driver driver : car.getDrivers()) {
                if (driverId.equals(driver.getId())) {
                    result.add(carConverter.convertCarToCarDto(car));
                }
            }
        }
        validator.validateResult(result);
        return result;
    }

    @Override
    public CarDto changeNumber(Long carId, String number) {
        validator.validateCarId(carId);
        CarDriverDto car = carConverter.convertCarToCarDriverDto(carRepository.findCarById(carId));
        car.setNumber(number);
        car.setLastModified(DateTimeMapper.date(new Date()));
        return carConverter.convertCarToCarDto(carRepository.save(carConverter.convertCarDriverDtoToCar(car)));
    }

    @Override
    public CarDto changeCost(Long carId, int cost) {
        CarDriverDto carDto = carConverter.convertCarToCarDriverDto(carRepository.findCarById(carId));
        carDto.setCost(cost);
        carDto.setLastModified(DateTimeMapper.date(new Date()));
        return carConverter.convertCarToCarDto(carRepository.save(carConverter.convertCarDriverDtoToCar(carDto)));
    }

    @Override
    public CarDriverDto attachDriverToCar(Long carId, Long driverId) {
        validator.validateBothId(carId, driverId);
        CarDriverDto carDriverDto = carConverter.convertCarToCarDriverDto(carRepository.findCarById(carId));
        Set<DriverDto> drivers = carDriverDto.getDrivers();
        DriverDto driverDto = driverConverter.convertDriverToDriverDto(driverRepository.findDriverById(driverId));
        driverDto.setLastModified(DateTimeMapper.date(new Date()));
        drivers.add(driverConverter.convertDriverToDriverDto
                (driverRepository.save(driverConverter.convertDriverDtoToDriver(driverDto))));
        carDriverDto.setDrivers(drivers);
        carDriverDto.setLastModified(DateTimeMapper.date(new Date()));
        return carConverter.convertCarToCarDriverDto
                (carRepository.save(carConverter.convertCarDriverDtoToCar(carDriverDto)));
    }

    @Override
    public CarDriverDto unpinDriverFromCar(Long carId, Long driverId) {
        validator.validateBothId(carId, driverId);
        CarDriverDto carDriverDto = carConverter.convertCarToCarDriverDto(carRepository.findCarById(carId));
        Set<DriverDto> drivers = carDriverDto.getDrivers();
        DriverDto driverDto = driverConverter.convertDriverToDriverDto(driverRepository.findDriverById(driverId));
        driverDto.setLastModified(DateTimeMapper.date(new Date()));
        drivers.remove(driverConverter.convertDriverToDriverDto
                (driverRepository.save(driverConverter.convertDriverDtoToDriver(driverDto))));
        carDriverDto.setDrivers(drivers);
        carDriverDto.setLastModified(DateTimeMapper.date(new Date()));
        return carConverter.convertCarToCarDriverDto
                (carRepository.save(carConverter.convertCarDriverDtoToCar(carDriverDto)));
    }


    @Override
    public int countCost() {
        int cost = 0;
        List<CarDto> cars = new ArrayList<>(0);
        for (Car car : carRepository.findAll()) {
            cars.add(carConverter.convertCarToCarDto(car));
        }
        for (CarDto car : cars) {
            cost += car.getCost();
        }
        return cost;
    }

    @Override
    public void deleteById(long id) {
        validator.validateCarId(id);
        carRepository.deleteById(id);
    }
}
