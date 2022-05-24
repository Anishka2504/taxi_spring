package edu.itstep.taxi.service.dto;

import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
public class CarDriverDto extends CarDto {

    Set<DriverDto> drivers;
}
