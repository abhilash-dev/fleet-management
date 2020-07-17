package com.abhilash.fleetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {
    @Id
    private String vin;
    private String make;
    private String model;
    private int year;
    private int redlineRpm;
    private int maxFuelVolume;
    private Instant lastServiceDate;
}
