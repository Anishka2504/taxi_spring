package edu.itstep.taxi.service;

import edu.itstep.taxi.service.dto.DriverDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DriverService {

    DriverDto addNewDriver(DriverDto driverDto);

    List<DriverDto> getAll(Integer pageNumber, Integer pageSize);

    DriverDto findOneById(long id);

    List<DriverDto> findAllByLastName(String lastName, Integer pageNumber, Integer pageSize);

    List<DriverDto> findAllByAge(int age, Integer pageNumber, Integer pageSize);

    DriverDto findDriverByDriverLicence(String driverLicence);

    DriverDto changeLastName(Long id, String lastName);

    DriverDto changeName(Long id, String name);

    DriverDto changeDriverLicence(Long id, String licence);

    void deleteById(long id);

}
