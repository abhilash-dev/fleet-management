package com.abhilash.fleetmanagement.service;

import com.abhilash.fleetmanagement.dao.VehicleDao;
import com.abhilash.fleetmanagement.dao.VehicleLocation;

import java.util.List;

public interface VehicleService {
    void addVehicles(List<VehicleDao> vehicles);

    List<VehicleDao> getAllVehicles();

    List<VehicleLocation> getLocationHistory(String vin);
}
