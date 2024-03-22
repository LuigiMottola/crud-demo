package com.example.cruddemo.Repository;

import com.example.cruddemo.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
