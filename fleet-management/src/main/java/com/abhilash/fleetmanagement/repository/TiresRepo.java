package com.abhilash.fleetmanagement.repository;

import com.abhilash.fleetmanagement.model.Tires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiresRepo extends JpaRepository<Tires, String> {
}
