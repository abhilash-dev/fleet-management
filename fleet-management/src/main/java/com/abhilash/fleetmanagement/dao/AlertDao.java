package com.abhilash.fleetmanagement.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class AlertDao {
    private int priority;
    private String vin;
    private Instant timestamp;
    private String description;
}
