package edu.itstep.taxi.service.converter;

import edu.itstep.taxi.entity.Car;
import edu.itstep.taxi.service.converter.uses.DateTimeMapper;
import edu.itstep.taxi.service.dto.CarDriverDto;
import edu.itstep.taxi.service.dto.CarDto;
import edu.itstep.taxi.service.dto.creation.CarCreationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface CarConverter {

    Car convertCarDtoToCar(CarDto carDto);

    Car convertCarDriverDtoToCar(CarDriverDto carDriverDto);

    CarDto convertCarToCarDto(Car car);

    CarDriverDto convertCarToCarDriverDto(Car car);

    CarCreationDto convertCarToCarCreationDto(Car car);

    Car convertCarCreationDtoToCar(CarCreationDto carCreationDto);
}
