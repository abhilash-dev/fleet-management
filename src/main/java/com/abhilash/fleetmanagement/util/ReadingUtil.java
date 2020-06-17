package com.abhilash.fleetmanagement.util;

import com.abhilash.fleetmanagement.dao.ReadingDao;
import com.abhilash.fleetmanagement.dao.VehicleLocation;
import com.abhilash.fleetmanagement.model.Reading;

public class ReadingUtil {

    private ReadingUtil() {

    }

    public static Reading mapToEntity(ReadingDao readingDao) {
        return Reading.builder()
                .checkEngineLightOn(readingDao.isCheckEngineLightOn())
                .cruiseControlOn(readingDao.isCruiseControlOn())
                .engineCoolantLow(readingDao.isEngineCoolantLow())
                .engineHp(readingDao.getEngineHp())
                .engineRpm(readingDao.getEngineRpm())
                .fuelVolume(readingDao.getFuelVolume())
                .latitude(readingDao.getLatitude())
                .longitude(readingDao.getLongitude())
                .speed(readingDao.getSpeed())
                .timestamp(readingDao.getTimestamp())
                .tires(readingDao.getTires())
                .vin(readingDao.getVin()).build();
    }

    public static ReadingDao mapToDao(Reading reading) {
        return ReadingDao.builder()
                .checkEngineLightOn(reading.isCheckEngineLightOn())
                .cruiseControlOn(reading.isCruiseControlOn())
                .engineCoolantLow(reading.isEngineCoolantLow())
                .engineHp(reading.getEngineHp())
                .engineRpm(reading.getEngineRpm())
                .fuelVolume(reading.getFuelVolume())
                .latitude(reading.getLatitude())
                .longitude(reading.getLongitude())
                .speed(reading.getSpeed())
                .timestamp(reading.getTimestamp())
                .tires(reading.getTires())
                .vin(reading.getVin()).build();
    }

    public static VehicleLocation mapToLocation(Reading reading) {
        return VehicleLocation.builder()
                .latitude(reading.getLatitude())
                .longitude(reading.getLongitude())
                .timestamp(reading.getTimestamp())
                .build();
    }
}
