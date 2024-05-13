package com.example.demo.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;

    }

    public List<Car> getAllCars() {

        return carRepository.findAll();
    }

    public void createCar(Car car) {

        Optional<Car> carOptional = carRepository.findCarByBrandAndModel(car.getBrand(), car.getModel());


        if (carOptional.isPresent()) {
            throw new IllegalStateException("Car with this model and brand already exists");
        }
        carRepository.save(car);
    }

    public void deleteCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalStateException("Car with id " + carId + " does not exist"));

        carRepository.deleteById(carId);
    }

    @Transactional
    public void updateCar(Long carId, String brand, String model) {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalStateException("Car with id " + carId + " does not exist"));

        if (brand != null && !brand.isEmpty() && !Objects.equals(car.getBrand(), brand)) {
            car.setBrand(brand);
        }

        if (model != null && !model.isEmpty() && !Objects.equals(car.getModel(), model)) {

            Optional<Car> carOptional = carRepository.findCarByBrandAndModel(brand, model);

            if (carOptional.isPresent()) {
                throw new IllegalStateException("Car with this brand and model already exists");
            }


            car.setModel(model);
        }

    }

}