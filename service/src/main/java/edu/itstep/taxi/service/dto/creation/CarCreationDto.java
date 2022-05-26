package edu.itstep.taxi.service.dto.creation;

import edu.itstep.taxi.service.CarService;
import lombok.Getter;
import lombok.Setter;

/**
 * This {@class CarCreationDto} class is intended for not to allow to influence from the outside on the values of ID,
 * date of creation and date of last modifying during creation of new car in the database. The values of ID, date of
 * creation and date of last modifying will be set automatically.
 * The only usage of this class is the method "addNewCar" in CarService and
 * CarController.
 *
 * @author Anna Drozdovskaya
 * @see CarService
 */

@Getter
@Setter
public class CarCreationDto {

    private String brand;

    private String model;

    private short year;

    private String number;

    private String equipment;

    private byte fuelConsumption;

    private int cost;
}
