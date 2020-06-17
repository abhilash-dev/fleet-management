package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.AlertDao;
import com.abhilash.fleetmanagement.service.AlertService;
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

@RestController
@RequestMapping(value = "/alerts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Slf4j
@Api(description = "Endpoints related to alerts for Fleet-management")
public class AlertController {
    private AlertService alertService;

    @ApiOperation(value = "Get a vehicle's all historical alerts", httpMethod = "GET")
    @GetMapping(value = "/{vin}")
    public ResponseEntity<List<AlertDao>> getVehiclesHistoricalAlerts(@ApiParam(value = "Vehicle Identifier (unique)", example = "1FMYU02143KB34432", required = true) @PathVariable("vin") String vin) {
        return ResponseEntity.ok(alertService.getVehiclesHistoricalAlerts(vin));
    }

    @ApiOperation(value = "Get all HIGH priority alerts for all vehicles within the past 2 hours", httpMethod = "GET")
    @GetMapping(value = "/high")
    public ResponseEntity<List<AlertDao>> getAllhighAlertsWithinTwoHours() {
        return ResponseEntity.ok(alertService.getAllHighAlertsWithinTime());
    }
}
