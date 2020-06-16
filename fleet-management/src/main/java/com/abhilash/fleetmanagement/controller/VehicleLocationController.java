package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.VehicleLocation;
import com.abhilash.fleetmanagement.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/location")
@RestController
@Slf4j
@AllArgsConstructor
public class VehicleLocationController {
    private final VehicleService vehicleService;

    @GetMapping("/{vin}")
    public ResponseEntity<List<VehicleLocation>> getVehicleLocation(@PathVariable("vin") String vin) {
        return ResponseEntity.ok(vehicleService.getLocationHistory(vin));
    }
}
