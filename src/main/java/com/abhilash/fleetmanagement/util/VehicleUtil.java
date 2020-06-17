package com.abhilash.fleetmanagement.util;

import com.abhilash.fleetmanagement.dao.VehicleDao;
import com.abhilash.fleetmanagement.model.Vehicle;

public class VehicleUtil {

    private VehicleUtil() {

    }

    public static VehicleDao mapToDao(Vehicle vehicle) {
        return VehicleDao.builder()
                .vin(vehicle.getVin())
                .lastServiceDate(vehicle.getLastServiceDate())
                .make(vehicle.getMake())
                .maxFuelVolume(vehicle.getMaxFuelVolume())
                .model(vehicle.getModel())
                .redlineRpm(vehicle.getRedlineRpm())
                .year(vehicle.getYear()).build();
    }

    public static Vehicle mapToEntity(VehicleDao vehicle) {
        return Vehicle.builder()
                .vin(vehicle.getVin())
                .lastServiceDate(vehicle.getLastServiceDate())
                .make(vehicle.getMake())
                .maxFuelVolume(vehicle.getMaxFuelVolume())
                .model(vehicle.getModel())
                .redlineRpm(vehicle.getRedlineRpm())
                .year(vehicle.getYear()).build();
    }
}
