package edu.itstep.taxi.service.dto.creation;

import edu.itstep.taxi.service.DriverService;
import lombok.Getter;
import lombok.Setter;

/**
 * This {@class DriverCreationDto} class is intended for not to allow to influence from the outside on the values of ID,
 * date of creation and date of last modifying during creation of new driver in the database. The values of ID, date of
 * creation and date of last modifying will be set automatically.
 * The only usage of this class is the method "addNewDriver" in DriverService and
 * DriverController.
 *
 * @author Anna Drozdovskaya
 * @see DriverService
 */

@Getter
@Setter
public class DriverCreationDto {

    private String lastName;
    private String name;
    private String middleName;
    private String dateOfBirth;
    private String driverLicence;
}
