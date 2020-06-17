package com.abhilash.fleetmanagement.repository;

import com.abhilash.fleetmanagement.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ReadingRepo extends JpaRepository<Reading, Long> {
    List<Reading> findAllByTimestampBetweenAndVinOrderByTimestampDesc(Instant from, Instant to, String vin);

    boolean existsByVin(String vin);
}
