package edu.itstep.taxi.service.converter;

import edu.itstep.taxi.entity.Driver;
import edu.itstep.taxi.service.converter.uses.DateTimeMapper;
import edu.itstep.taxi.service.dto.DriverDto;
import edu.itstep.taxi.service.dto.creation.DriverCreationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface DriverConverter {

    Driver convertDriverDtoToDriver(DriverDto driverDto);

    DriverDto convertDriverToDriverDto(Driver driver);

    //    ------------------------------------------------------------------------------------------------------------------

    Driver convertDriverCreationDtoToDriver(DriverCreationDto driverCreationDto);

    DriverCreationDto convertDriverToDriverCreationDto(Driver driver);

}
