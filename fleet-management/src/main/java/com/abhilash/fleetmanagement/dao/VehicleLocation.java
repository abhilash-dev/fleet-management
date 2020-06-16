package com.abhilash.fleetmanagement.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleLocation {
    private double latitude;
    private double longitude;
    private Instant timestamp;
}
