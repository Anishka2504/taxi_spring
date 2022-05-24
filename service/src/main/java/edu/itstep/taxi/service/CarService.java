package edu.itstep.taxi.service;

import edu.itstep.taxi.service.dto.CarDriverDto;
import edu.itstep.taxi.service.dto.CarDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CarService {

    CarDto addNewCar(CarDto carDto);

    List<CarDriverDto> getAllCars(Integer pageNumber, Integer pageSize);

    CarDriverDto findCarById(long id);

    List<CarDto> findCarByEquipment(String equipment, Integer pageNumber, Integer pageSize);

    List<CarDto> findCarByYear(short year, Integer pageNumber, Integer pageSize);

    List<CarDto> findCarByDriver(Long driverId, Integer pageNumber, Integer pageSize);

    CarDto changeNumber(Long carId, String number);

    CarDto changeCost(Long carId, int cost);

    CarDriverDto attachDriverToCar(Long carId, Long driverId);

    CarDriverDto unpinDriverFromCar(Long carId, Long driverId);

    void deleteById(long id);

    int countCost();
}
