package com.abhilash.fleetmanagement.service;

import com.abhilash.fleetmanagement.dao.VehicleDao;
import com.abhilash.fleetmanagement.dao.VehicleLocation;
import com.abhilash.fleetmanagement.exception.ResourceNotFoundException;
import com.abhilash.fleetmanagement.model.Reading;
import com.abhilash.fleetmanagement.model.Vehicle;
import com.abhilash.fleetmanagement.repository.ReadingRepo;
import com.abhilash.fleetmanagement.repository.VehicleRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return vehicleRepo.findAll().stream().map(this::mapToDao).collect(Collectors.toList());
    }

    private VehicleDao mapToDao(Vehicle vehicle) {
        return VehicleDao.builder()
                .vin(vehicle.getVin())
                .lastServiceDate(vehicle.getLastServiceDate())
                .make(vehicle.getMake())
                .maxFuelVolume(vehicle.getMaxFuelVolume())
                .model(vehicle.getModel())
                .redlineRpm(vehicle.getRedlineRpm())
                .year(vehicle.getYear()).build();
    }

    private Vehicle mapToEntity(VehicleDao vehicle) {
        return Vehicle.builder()
                .vin(vehicle.getVin())
                .lastServiceDate(vehicle.getLastServiceDate())
                .make(vehicle.getMake())
                .maxFuelVolume(vehicle.getMaxFuelVolume())
                .model(vehicle.getModel())
                .redlineRpm(vehicle.getRedlineRpm())
                .year(vehicle.getYear()).build();
    }

    @Override
    public List<VehicleLocation> getLocationHistory(String vin) {
        if (!vehicleRepo.existsById(vin)) {
            throw new ResourceNotFoundException("Invalid vin: " + vin + "No vehicles found!");
        }

        if (!readingRepo.existsByVin(vin)) {
            return Collections.emptyList();
        }

        Instant to = Instant.now();
        Instant from = to.minus(30, ChronoUnit.MINUTES);
        return readingRepo.findAllByTimestampBetweenAndVinOrderByTimestampDesc(from, to, vin).stream().map(this::mapToLocation).collect(Collectors.toList());
    }

    private VehicleLocation mapToLocation(Reading reading) {
        return VehicleLocation.builder()
                .latitude(reading.getLatitude())
                .longitude(reading.getLongitude())
                .timestamp(reading.getTimestamp())
                .build();
    }

    @Override
    public void addVehicles(List<VehicleDao> vehicles) {
        vehicles.stream().map(this::mapToEntity).forEach(vehicleRepo::save);
    }
}