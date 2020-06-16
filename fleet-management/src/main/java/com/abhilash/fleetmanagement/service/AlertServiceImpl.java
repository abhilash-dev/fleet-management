package com.abhilash.fleetmanagement.service;

import com.abhilash.fleetmanagement.exception.ResourceNotFoundException;
import com.abhilash.fleetmanagement.model.Reading;
import com.abhilash.fleetmanagement.model.Vehicle;
import com.abhilash.fleetmanagement.repository.ReadingRepo;
import com.abhilash.fleetmanagement.repository.VehicleRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {
    private final VehicleRepo vehicleRepo;
    private final ReadingRepo readingRepo;
    private final Rules rules;
    private final RulesEngine rulesEngine;


    @Override
    public void check(long readingId) {
        Reading reading = readingRepo.getOne(readingId);
        Vehicle vehicle = Optional.of(vehicleRepo.getOne(reading.getVin())).orElseThrow(
                () -> new ResourceNotFoundException("Invalid VIN:" + reading.getVin() + " Vehicle not found!"));
        Facts facts = new Facts();
        facts.put("reading", reading);
        facts.put("vehicle", vehicle);

        rulesEngine.fire(rules, facts);
    }
}
