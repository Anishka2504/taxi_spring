package edu.itstep.taxi.service.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CarDriverDto extends CarDto {

    Set<DriverDto> drivers;
}
