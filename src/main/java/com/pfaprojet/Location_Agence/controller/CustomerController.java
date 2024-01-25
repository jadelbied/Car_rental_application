package com.pfaprojet.Location_Agence.controller;

import com.pfaprojet.Location_Agence.dto.BookACarDto;
import com.pfaprojet.Location_Agence.dto.CarDto;
import com.pfaprojet.Location_Agence.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars(){
        List<CarDto> carDtoList=customerService.getAllCars();
        return ResponseEntity.ok(carDtoList);
    }
    @PostMapping("/car/book")
    public ResponseEntity<Void> bookACar(@RequestBody BookACarDto bookACarDto){
        boolean success=customerService.bookACar(bookACarDto);
        if(success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId){
        CarDto carDto=customerService.getCarById(carId);
        if(carDto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carDto);
    }
}
