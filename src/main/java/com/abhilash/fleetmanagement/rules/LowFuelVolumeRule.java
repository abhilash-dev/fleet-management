package com.abhilash.fleetmanagement.rules;

import com.abhilash.fleetmanagement.model.Alert;
import com.abhilash.fleetmanagement.model.AlertPriority;
import com.abhilash.fleetmanagement.model.Reading;
import com.abhilash.fleetmanagement.model.Vehicle;
import com.abhilash.fleetmanagement.repository.AlertRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
@Rule(name = "Low Fuel Volume Rule", description = "Rule: Fuel Volume < 10% maxFuelVolume, Priority: Medium", priority = 1)
public class LowFuelVolumeRule {
    private AlertRepo alertRepo;

    @Condition
    public boolean isAlert(@Fact(value = "vehicle") Vehicle vehicle, @Fact(value = "reading") Reading reading) {
        return reading.getFuelVolume() < (vehicle.getMaxFuelVolume() * 0.1);
    }

    @Action
    public void addAlert(@Fact(value = "reading") Reading reading) {
        alertRepo.save(Alert.builder()
                .readingId(reading.getId())
                .timestamp(reading.getTimestamp())
                .priority(AlertPriority.MEDIUM)
                .vin(reading.getVin())
                .description("Rule: Fuel Volume < 10% maxFuelVolume")
                .build());
    }
}
