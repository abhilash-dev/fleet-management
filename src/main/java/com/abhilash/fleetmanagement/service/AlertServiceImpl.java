package com.abhilash.fleetmanagement.service;

import com.abhilash.fleetmanagement.dao.AlertDao;
import com.abhilash.fleetmanagement.exception.BadRequestException;
import com.abhilash.fleetmanagement.exception.ResourceNotFoundException;
import com.abhilash.fleetmanagement.model.AlertPriority;
import com.abhilash.fleetmanagement.model.Reading;
import com.abhilash.fleetmanagement.model.Vehicle;
import com.abhilash.fleetmanagement.repository.AlertRepo;
import com.abhilash.fleetmanagement.repository.ReadingRepo;
import com.abhilash.fleetmanagement.repository.VehicleRepo;
import com.abhilash.fleetmanagement.util.Alertutil;
import com.abhilash.fleetmanagement.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {
    private final AlertRepo alertRepo;
    private final VehicleRepo vehicleRepo;
    private final ReadingRepo readingRepo;
    private final Rules rules;
    private final RulesEngine rulesEngine;


    @Override
    public List<AlertDao> getVehiclesHistoricalAlerts(String vin) {
        if (!StringUtils.hasText(vin)) {
            throw new BadRequestException("Bad Request: Invalid vin - " + vin);
        }

        if (!vehicleRepo.existsById(vin)) {
            throw new ResourceNotFoundException("Invalid vin: " + vin + "No vehicles found!");
        }

        return alertRepo.findAllByVinOrderByTimestampDesc(vin).stream()
                .map(Alertutil::mapToDao)
                .collect(Collectors.toList());
    }

    @Override
    public List<AlertDao> getAllHighAlertsWithinTime() {
        return getAllHighAlertsWithinTime(2, ChronoUnit.HOURS);
    }

    @Override
    public List<AlertDao> getAllHighAlertsWithinTime(int differenceFromNow, ChronoUnit unit) {
        Instant to = Instant.now();
        Instant from = to.minus(differenceFromNow, unit);

        return alertRepo.findAllByPriorityAndTimestampBetweenOrderByTimestampDesc(AlertPriority.HIGH, from, to).stream()
                .map(Alertutil::mapToDao)
                .collect(Collectors.toList());
    }

    @Override
    public void check(long readingId) {
        Reading reading = readingRepo.getOne(readingId);
        Vehicle vehicle = Optional.of(vehicleRepo.getOne(reading.getVin()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid VIN:" + reading.getVin() + " Vehicle not found!"));
        Facts facts = new Facts();
        facts.put(Constants.FACT_READING, reading);
        facts.put(Constants.FACT_VEHICLE, vehicle);

        rulesEngine.fire(rules, facts);
    }
}