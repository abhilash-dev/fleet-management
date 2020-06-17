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
@Rule(name = "Incorrect RPM rule", description = "Rule: engineRpm > redlineRpm, Priority: HIGH", priority = 0)
public class IncorrectRpmRule {
    private AlertRepo alertRepo;

    @Condition
    public boolean isAlert(@Fact(value = "vehicle") Vehicle vehicle, @Fact(value = "reading") Reading reading) {
        return reading.getEngineRpm() > vehicle.getRedlineRpm();
    }

    @Action
    public void addAlert(@Fact(value = "reading") Reading reading) {
        alertRepo.save(Alert.builder()
                .readingId(reading.getId())
                .timestamp(reading.getTimestamp())
                .priority(AlertPriority.HIGH)
                .vin(reading.getVin())
                .description("Rule: engineRpm > redlineRpm")
                .build());
    }
}
