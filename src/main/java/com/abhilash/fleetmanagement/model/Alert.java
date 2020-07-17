package com.abhilash.fleetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private AlertPriority priority;
    private String vin;
    private Instant timestamp;
    private String description;
    private long readingId;
}
