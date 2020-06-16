package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.VehicleDao;
import com.abhilash.fleetmanagement.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://mocker.egen.academy"}, maxAge = 600, methods = {RequestMethod.PUT, RequestMethod.OPTIONS})
@RequestMapping(value = "/vehicles")
@RestController
@Slf4j
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PutMapping
    public ResponseEntity addVehicles(@RequestBody List<VehicleDao> vehicles) {
        vehicleService.addVehicles(vehicles);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<VehicleDao>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }
}
