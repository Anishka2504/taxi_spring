package edu.itstep.taxi.service.dto;

import edu.itstep.taxi.service.dto.parent.LogDto;
import lombok.Data;

@Data
public class CarDto extends LogDto {

    private String brand;

    private String model;

    private short year;

    private String number;

    private String equipment;

    private byte fuelConsumption;

    private int cost;
}
