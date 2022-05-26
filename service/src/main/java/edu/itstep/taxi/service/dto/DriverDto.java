package edu.itstep.taxi.service.dto;

import edu.itstep.taxi.service.dto.parent.LogDto;
import lombok.Data;

@Data
public class DriverDto extends LogDto {

    private String lastName;
    private String name;
    private String middleName;
    private String dateOfBirth;
    private String driverLicence;
}
