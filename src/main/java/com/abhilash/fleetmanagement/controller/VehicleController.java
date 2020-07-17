package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.VehicleDao;
import com.abhilash.fleetmanagement.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://mocker.egen.academy"}, maxAge = 600, methods = {RequestMethod.PUT, RequestMethod.OPTIONS})
@RequestMapping(value = "/vehicles", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@Slf4j
@AllArgsConstructor
@Api(description = "Endpoints related to Vehicles for Fleet-management")
public class VehicleController {
    private final VehicleService vehicleService;

    @ApiOperation(value = "Create / Update vehicle's data", httpMethod = "PUT")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addVehicles(@RequestBody List<VehicleDao> vehicles) {
        vehicleService.addVehicles(vehicles);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ApiOperation(value = "Get all vehicles data", httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<VehicleDao>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }
}
