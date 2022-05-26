package edu.itstep.taxi.controller;

import edu.itstep.taxi.service.DriverService;
import edu.itstep.taxi.service.dto.DriverDto;
import edu.itstep.taxi.service.dto.creation.DriverCreationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.itstep.taxi.service.constant.Constant.PAGE_SIZE;

@RestController
@RequestMapping("/api")
@Tag(name = "Driver menu", description = "Operations with drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    //-----operations--------------------------------------------------------------------------------------------------

    @PostMapping("/drivers/driver")
    @Operation(summary = "Add new driver",
            description = "Enter last name, name, middle name, date of birth (dd.mm.yyyy) and the number of" +
                    " driver licence of a driver to add one to the depot.")
    public DriverCreationDto addNewDriver(@RequestBody DriverCreationDto driverCreationDto) {
        return driverService.addNewDriver(driverCreationDto);
    }

    @GetMapping("/drivers")
    @Operation(summary = "Get all drivers (paged)", description = "Show all drivers in the depot. " +
            "Enter the number of page to see its content")
    public List<DriverDto> getAll(@RequestParam Integer pageNumber) {
        return driverService.getAll(pageNumber - 1, PAGE_SIZE);
    }

    @GetMapping("/drivers/find_by_id")
    @Operation(summary = "Search by ID", description = "enter ID to find corresponding driver")
    public DriverDto findOneById(@RequestParam long id) {
        return driverService.findOneById(id);
    }

    @GetMapping("/drivers/find_by_lastname")
    @Operation(summary = "Search by lastname (paged and sorted)",
            description = "Enter lastname to find all drivers with this one. " +
                    "Drivers in result list are sorted by date of birth from young to old. " +
                    "Enter the number of page to see its content")
    public List<DriverDto> findAllByLastName(@RequestParam String lastName, @RequestParam Integer pageNumber) {
        return driverService.findAllByLastName(lastName, pageNumber - 1, PAGE_SIZE);
    }

    @GetMapping("/drivers/find_by_age")
    @Operation(summary = "Search by age (paged and sorted).",
            description = "Shows all drivers whose age equals to entered one. " +
                    "Drivers in the result list are sorted by last name (A - Z). " +
                    "Enter the number of page to see its content.")
    public List<DriverDto> findAllByAge(@RequestParam int age, @RequestParam Integer pageNumber) {
        return driverService.findAllByAge(age, pageNumber - 1, PAGE_SIZE);
    }

    @GetMapping("/drivers/find_by_license")
    @Operation(summary = "Search by driver licence number",
            description = "enter the number of driver licence to find a driver")
    public DriverDto findDriverByDriverLicence(@RequestParam String driverLicence) {
        return driverService.findDriverByDriverLicence(driverLicence);
    }

    @PutMapping("/drivers/change_last_name")
    @Operation(summary = "Changing of driver's last name",
            description = "Enter ID of the driver whose last name should be changed and then enter new last name")
    public DriverDto changeLastName(@RequestParam Long id, @RequestParam String lastName) {
        return driverService.changeLastName(id, lastName);
    }

    @PutMapping("/drivers/change_name")
    @Operation(summary = "Changing of driver's name",
            description = "Enter ID of the driver whose name should be changed and then enter new name")
    public DriverDto changeName(@RequestParam Long id, @RequestParam String name) {
        return driverService.changeName(id, name);
    }

    @PutMapping("/drivers/change_driver_licence")
    @Operation(summary = "Changing of driver's licence number",
            description = "Enter ID of the driver whose licence data should be changed and then enter new data")
    public DriverDto changeDriverLicence(@RequestParam Long id, @RequestParam String licence) {
        return driverService.changeDriverLicence(id, licence);
    }

    @DeleteMapping("/drivers/delete_by_id")
    @Operation(summary = "Delete driver", description = "enter ID of the driver to delete the one from the depot")
    public void deleteDriverById(@RequestParam long id) {
        driverService.deleteById(id);
    }
}
