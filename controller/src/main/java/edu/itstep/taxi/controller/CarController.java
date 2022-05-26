package edu.itstep.taxi.controller;

import edu.itstep.taxi.service.CarService;
import edu.itstep.taxi.service.dto.CarDriverDto;
import edu.itstep.taxi.service.dto.CarDto;
import edu.itstep.taxi.service.dto.creation.CarCreationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.itstep.taxi.service.constant.Constant.PAGE_SIZE;

@RestController
@RequestMapping("/api")
@Tag(name = "Car menu", description = "Operations with cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //-----operations-------------------------------------------------------------------------

    @PostMapping("/cars/car")
    @Operation(summary = "Add new car",
            description = "Enter brand, model, year of issue, number, type of equipment, value of fuel consumption" +
                    " and cost of a car to add one to the depot.")
    public CarCreationDto addNewCar(@RequestBody CarCreationDto carCreationDto) {
        return carService.addNewCar(carCreationDto);
    }

    @GetMapping("/cars")
    @Operation(summary = "Get all cars (paged)",
            description = "Shows all cars by pages. Enter number of page to see its content")
    public List<CarDriverDto> getAllCarsPaged(@RequestParam Integer pageNumber) {
        return carService.getAllCars(pageNumber - 1, PAGE_SIZE);
    }

    @GetMapping("/cars/find_by_year")
    @Operation(summary = "Search by issue year (paged)",
            description = "Enter year of issue to find all cars of corresponding year." +
                    "Enter the number of page to see its content")
    public List<CarDto> findCarByYear(@RequestParam short year, @RequestParam Integer pageNumber) {
        return carService.findCarByYear(year, pageNumber - 1, PAGE_SIZE);
    }

    @GetMapping("/cars/find_by_equipment")
    @Operation(summary = "Search by equipment (paged and sorted)",
            description = "Enter type of equipment to find all cars with corresponding one. " +
                    "Cars in result list are sorted by year of issue (from new to old). " +
                    "Enter the number of page to see its content")
    public List<CarDto> findCarByEquipment(@RequestParam String equipment,
                                           @RequestParam Integer pageNumber) {
        return carService.findCarByEquipment(equipment, pageNumber - 1, PAGE_SIZE);
    }

    @GetMapping("/cars/find_by_driver_id")
    @Operation(summary = "Search by driver (paged and sorted)",
            description = "Enter ID of the driver to find all cars the one is attached to." +
                    "Enter the number of page to see its content. " +
                    "Cars founded by request will be sorted by equipment and by year of issue (from old to new)")
    public List<CarDto> findCarByDriver(@RequestParam Long driverId, @RequestParam Integer pageNumber) {
        return carService.findCarByDriver(driverId, pageNumber - 1, PAGE_SIZE);
    }

    @PutMapping("/cars/attach_driver")
    @Operation(summary = "Attach driver to the car",
            description = "Enter ID of a driver and ID of a car to add the driver to the list of this car drivers.")
    public CarDriverDto attachDriverToCar(@RequestParam Long carId, @RequestParam Long driverId) {
        return carService.attachDriverToCar(carId, driverId);
    }

    @PutMapping("/cars/unpin_driver")
    @Operation(summary = "Unpin driver from the car",
            description = "Enter ID of a driver and ID of a car to remove the driver from the list of this car drivers.")
    public CarDriverDto unpinDriverFromCar(@RequestParam Long carId, @RequestParam Long driverId) {
        return carService.unpinDriverFromCar(carId, driverId);
    }

    @GetMapping("/cars/find_by_id")
    @Operation(summary = "Search by ID", description = "enter ID value to find corresponding car")
    public CarDriverDto findCarByIdWithDrivers(@RequestParam long id) {
        return carService.findCarById(id);
    }

    @PutMapping("/cars/car/change_number")
    @Operation(summary = "Changing of car number",
            description = "enter ID of the car you want change number of and then enter new number")
    public CarDto changeNumber(@RequestParam Long carId, @RequestParam String number) {
        return carService.changeNumber(carId, number);
    }

    @PutMapping("/cars/car/change_cost")
    @Operation(summary = "Changing of car cost",
            description = "enter ID of the car you want change number of and then enter new cost value")
    public CarDto changeCost(@RequestParam Long carId, @RequestParam int cost) {
        return carService.changeCost(carId, cost);
    }

    @DeleteMapping("/cars/delete_by_id")
    @Operation(summary = "Delete car", description = "enter ID of the car to delete corresponding one from the depot")
    public void deleteCarById(@RequestParam long id) {
        carService.deleteById(id);
    }

    @GetMapping("/cars/total_cost")
    @Operation(summary = "Count total cost of the depot")
    public int countCost() {
        return carService.countCost();
    }
}
