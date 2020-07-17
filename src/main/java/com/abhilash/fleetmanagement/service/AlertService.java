package com.abhilash.fleetmanagement.service;

import com.abhilash.fleetmanagement.dao.AlertDao;

import java.time.temporal.ChronoUnit;
import java.util.List;

public interface AlertService {
    void check(long readingId);

    List<AlertDao> getVehiclesHistoricalAlerts(String vin);

    List<AlertDao> getAllHighAlertsWithinTime();

    List<AlertDao> getAllHighAlertsWithinTime(int differenceFromNow, ChronoUnit unit);
}
