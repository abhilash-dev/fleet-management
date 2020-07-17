package com.abhilash.fleetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private Tires tires;
}