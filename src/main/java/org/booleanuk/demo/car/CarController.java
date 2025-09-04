package org.booleanuk.demo.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarRepository repository;

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        return ResponseEntity.ok(repository.save(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable int id) {
        return ResponseEntity.ok(repository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable int id, @RequestBody Car body) {
        Optional<Car> car = repository.findById(id);
        if (car.isPresent()) {
            car.get().setBrand(body.getBrand());
            car.get().setType(body.getType());
            car.get().setHorsepower(body.getHorsepower());

            return ResponseEntity.ok(repository.save(car.get()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.ok("DELETE MANNEN");
    }



    @GetMapping("/seed")
    public ResponseEntity<?> seed() {
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
        return ResponseEntity.ok(repository.saveAll(cars));
    }

}
