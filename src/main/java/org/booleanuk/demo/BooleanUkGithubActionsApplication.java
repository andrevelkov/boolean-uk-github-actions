package org.booleanuk.demo;

import org.booleanuk.demo.car.Car;
import org.booleanuk.demo.car.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BooleanUkGithubActionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooleanUkGithubActionsApplication.class, args);


	}

    @Bean
    CommandLineRunner seed(CarRepository repository) {
        return args -> {
            List<Car> cars = List.of(
                    new Car("BMW", "Sedan", 248),
                    new Car("Audi", "SUV", 320),
                    new Car("Mercedes", "Coupe", 300),
                    new Car("Toyota", "Hatchback", 150),
                    new Car("Honda", "Sedan", 180),
                    new Car("Ford", "Truck", 400),
                    new Car("Chevrolet", "SUV", 280),
                    new Car("Tesla", "Sedan", 450),
                    new Car("Nissan", "Crossover", 200),
                    new Car("Lexus", "Luxury", 350)
            );
            repository.saveAll(cars);
        };
    }

}
