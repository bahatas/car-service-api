package com.group3.controller;


import com.group3.dto.Car;
import com.group3.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cars")
@Slf4j
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") Long carId) throws IOException {

        log.info("Received get all car request");

        //go to service and fetch data
        Car car =  carService.getCar(carId);
        return  ResponseEntity.ok().body(car);  //status code for ok ? 200  201 :Created 404: 401: 403: 415: 503:

    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Car>> getCars() throws IOException { // checked exception

        log.info("Received get all car request");
        List<Car> cars = carService.getCars();
        return ResponseEntity.ok().body(cars);

    }
}
