package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.AlertDao;
import com.abhilash.fleetmanagement.service.AlertService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/alerts")
@AllArgsConstructor
@Slf4j
public class AlertController {
    private AlertService alertService;

    @GetMapping(value = "/{VIN}")
    public ResponseEntity<List<AlertDao>> getVehiclesHistoricalAlerts(@PathVariable String vin) {
        return ResponseEntity.ok(alertService.getVehiclesHistoricalAlerts(vin));
    }

    @GetMapping(value = "/high")
    public ResponseEntity<List<AlertDao>> getAllhighAlertsWithinTwoHours() {
        return ResponseEntity.ok(alertService.getAllHighAlertsWithinTime());
    }
}
