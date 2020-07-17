package com.abhilash.fleetmanagement.service;

import com.abhilash.fleetmanagement.dao.VehicleDao;
import com.abhilash.fleetmanagement.dao.VehicleLocation;
import com.abhilash.fleetmanagement.exception.BadRequestException;
import com.abhilash.fleetmanagement.exception.ResourceNotFoundException;
import com.abhilash.fleetmanagement.repository.ReadingRepo;
import com.abhilash.fleetmanagement.repository.VehicleRepo;
import com.abhilash.fleetmanagement.util.ReadingUtil;
import com.abhilash.fleetmanagement.util.VehicleUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepo vehicleRepo;
    private final ReadingRepo readingRepo;

    @Override
    public List<VehicleDao> getAllVehicles() {
        return vehicleRepo.findAll().stream()
                .map(VehicleUtil::mapToDao)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleLocation> getLocationHistory(String vin) {
        if (!StringUtils.hasText(vin)) {
            throw new BadRequestException("Bad Request: Invalid vin - " + vin);
        }

        if (!vehicleRepo.existsById(vin)) {
            throw new ResourceNotFoundException("Invalid vin: " + vin + "No vehicles found!");
        }

        if (!readingRepo.existsByVin(vin)) {
            return Collections.emptyList();
        }

        Instant to = Instant.now();
        Instant from = to.minus(30, ChronoUnit.MINUTES);
        return readingRepo.findAllByTimestampBetweenAndVinOrderByTimestampDesc(from, to, vin).stream()
                .map(ReadingUtil::mapToLocation)
                .collect(Collectors.toList());
    }

    @Override
    public void addVehicles(List<VehicleDao> vehicles) {
        vehicles.stream()
                .map(VehicleUtil::mapToEntity)
                .forEach(vehicleRepo::save);
    }
}