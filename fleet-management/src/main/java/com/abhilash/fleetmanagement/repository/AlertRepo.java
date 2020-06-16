package com.abhilash.fleetmanagement.repository;

import com.abhilash.fleetmanagement.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AlertRepo extends JpaRepository<Alert, Long> {
    List<Alert> findAllByVinOrderByTimestampDesc(String vin);

    List<Alert> findAllByTimestampBetweenOrderByTimestampDesc(Instant from, Instant to);
}
