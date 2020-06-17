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

import static com.abhilash.fleetmanagement.util.ReadingUtil.mapToEntity;

@Service
@Slf4j
@AllArgsConstructor
public class ReadingServiceImpl implements ReadingService {
    private final ReadingRepo readingRepo;
    private final VehicleRepo vehicleRepo;
    private final TiresRepo tiresRepo;

    @Override
    public long addReading(ReadingDao readingDao) {
        if (vehicleRepo.existsById(readingDao.getVin())) {
            Reading reading = mapToEntity(readingDao);
            tiresRepo.save(reading.getTires());
            Reading savedReading = readingRepo.save(reading);
            return savedReading.getId();
        } else {
            throw new ResourceNotFoundException("The Vehicle with the following ID:" + readingDao.getVin() + " doesn't exist");
        }
    }
}
