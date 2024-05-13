package com.example.demo.car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/car")
@CrossOrigin(origins = "http://localhost:5173")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public void createCar(@RequestBody Car car) {
        carService.createCar(car);
    }

    @DeleteMapping(path = "{carId}")
    public void deleteCar(@PathVariable("carId") Long carId) {
        carService.deleteCar(carId);
    }

    @PutMapping(path = "{carId}")
    public void updateStudent(@PathVariable("carId") Long studentId,
                              @RequestParam(required = false) String brand,
                              @RequestParam(required = false) String model) {
        carService.updateCar(studentId, brand, model);
    }

}
