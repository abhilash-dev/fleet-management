package com.abhilash.fleetmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AlertPriority {
    HIGH(0),
    MEDIUM(1),
    LOW(2);

    @Getter
    private int priority;
}
