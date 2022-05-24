package edu.itstep.taxi.service.converter;

import edu.itstep.taxi.entity.Driver;
import edu.itstep.taxi.service.converter.uses.DateTimeMapper;
import edu.itstep.taxi.service.dto.DriverDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface DriverConverter {

    Driver convertDriverDtoToDriver(DriverDto driverDto);

//    @Mapping(source = "dateCreation", target = "log.dateCreation")
//    @Mapping(source = "lastModified", target = "log.lastModified")
//    @Mapping(source = "version", target = "log.version")
    DriverDto convertDriverToDriverDto(Driver driver);

}
