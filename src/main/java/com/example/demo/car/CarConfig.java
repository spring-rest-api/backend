package com.example.demo.car;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CarConfig {

    @Bean
    CommandLineRunner commandLineRunner(CarRepository repository) {
        return args -> {

            List<Car> cars = repository.findAll();
            repository.saveAll(cars);
        };

    };
}
