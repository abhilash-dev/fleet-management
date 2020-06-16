package com.abhilash.fleetmanagement.service;

import com.abhilash.fleetmanagement.dao.ReadingDao;
import com.abhilash.fleetmanagement.exception.ResourceNotFoundException;
import com.abhilash.fleetmanagement.model.Reading;
import com.abhilash.fleetmanagement.repository.ReadingRepo;
import com.abhilash.fleetmanagement.repository.TiresRepo;
import com.abhilash.fleetmanagement.repository.VehicleRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ReadingServiceImpl implements ReadingService {
    private final ReadingRepo readingRepo;
    private final VehicleRepo vehicleRepo;
    private final TiresRepo tiresRepo;

    @Override
    public void addReading(ReadingDao readingDao) {
        if (vehicleRepo.existsById(readingDao.getVin())) {
            Reading reading = mapToEntity(readingDao);
            tiresRepo.save(reading.getTires());
            readingRepo.save(reading);
        } else {
            throw new ResourceNotFoundException("The Vehicle with the following ID:" + readingDao.getVin() + " doesn't exist");
        }
    }

    private Reading mapToEntity(ReadingDao readingDao) {
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

    private ReadingDao mapToDao(Reading reading) {
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
}
