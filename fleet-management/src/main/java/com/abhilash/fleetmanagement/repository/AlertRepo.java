package com.abhilash.fleetmanagement.repository;

import com.abhilash.fleetmanagement.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepo extends JpaRepository<Alert, Long> {
}
