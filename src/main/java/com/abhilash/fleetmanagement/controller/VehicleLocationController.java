package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.VehicleLocation;
import com.abhilash.fleetmanagement.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/location", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@Slf4j
@AllArgsConstructor
@Api(description = "Endpoints related to vehicle location for Fleet-management")
public class VehicleLocationController {
    private final VehicleService vehicleService;

    @ApiOperation(value = "Get a vehicle's location history for the past 30 minutes", httpMethod = "GET")
    @GetMapping(value = "/{vin}")
    public ResponseEntity<List<VehicleLocation>> getVehicleLocation(@ApiParam(value = "Vehicle Identifier (unique)", example = "1FMYU02143KB34432", required = true) @PathVariable("vin") String vin) {
        return ResponseEntity.ok(vehicleService.getLocationHistory(vin));
    }
}
