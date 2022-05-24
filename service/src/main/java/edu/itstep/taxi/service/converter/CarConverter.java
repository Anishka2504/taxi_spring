package edu.itstep.taxi.service.converter;

import edu.itstep.taxi.entity.Car;
import edu.itstep.taxi.service.converter.uses.DateTimeMapper;
import edu.itstep.taxi.service.dto.CarDriverDto;
import edu.itstep.taxi.service.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface CarConverter {

    Car convertCarDtoToCar(CarDto carDto);

    Car convertCarDriverDtoToCar(CarDriverDto carDriverDto);

//    @Mapping(source = "dateCreation", target = "log.dateCreation")
//    @Mapping(source = "lastModified", target = "log.lastModified")
//    @Mapping(source = "version", target = "log.version")
    CarDto convertCarToCarDto(Car car);

//    @Mapping(source = "dateCreation", target = "log.dateCreation")
//    @Mapping(source = "lastModified", target = "log.lastModified")
//    @Mapping(source = "version", target = "log.version")
    CarDriverDto convertCarToCarDriverDto(Car car);
}
