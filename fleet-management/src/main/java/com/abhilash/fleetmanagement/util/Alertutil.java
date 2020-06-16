package com.abhilash.fleetmanagement.util;

import com.abhilash.fleetmanagement.dao.AlertDao;
import com.abhilash.fleetmanagement.model.Alert;

public class Alertutil {
    private Alertutil() {

    }

    public static AlertDao mapToDao(Alert alert) {
        return AlertDao.builder()
                .description(alert.getDescription())
                .timestamp(alert.getTimestamp())
                .vin(alert.getVin())
                .priority(alert.getPriority().getPriority())
                .build();
    }
}
