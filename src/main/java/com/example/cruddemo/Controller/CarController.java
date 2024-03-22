package com.example.cruddemo.Controller;

import com.example.cruddemo.Entity.Car;
import com.example.cruddemo.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarService carService;

    @PostMapping("/create")
    public ResponseEntity<Car> createCar(@RequestBody Car carToCreate){
        Car createdCar = carService.createCar(carToCreate);
        return ResponseEntity.ok(createdCar);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> carsList = carService.getAllCars();
        return ResponseEntity.ok(carsList);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Optional<Car> carFound = carService.getCarById(id);
        if (carFound.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(carFound.get());
    }

    @PutMapping("/editType/{id}")
    public ResponseEntity<Car> updateCarType(@PathVariable Long id, @RequestParam String type){
        Optional<Car> updatedCar = carService.updateCarType(id, type);
        if (updatedCar.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedCar.get());
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<List<Car>> deleteAllCars(){
        List<Car> deletedCars = carService.deleteAllCars();
        return ResponseEntity.ok(deletedCars);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable Long id){
        Optional<Car> deletedCar = carService.deleteSpecificCar(id);
        if (deletedCar.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(deletedCar.get());

    }
}
