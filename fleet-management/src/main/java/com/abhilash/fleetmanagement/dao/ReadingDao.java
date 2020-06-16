package com.abhilash.fleetmanagement.dao;

import com.abhilash.fleetmanagement.model.Tires;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadingDao {
    private String vin;
    private double latitude;
    private double longitude;
    private Instant timestamp;
    private double fuelVolume;
    private int speed;
    private int engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private int engineRpm;
    private Tires tires;
}
