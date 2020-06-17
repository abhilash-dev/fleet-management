package com.abhilash.fleetmanagement.repository;

import com.abhilash.fleetmanagement.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, String> {
}
