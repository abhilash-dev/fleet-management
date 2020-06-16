package com.abhilash.fleetmanagement.rules;

import com.abhilash.fleetmanagement.model.Alert;
import com.abhilash.fleetmanagement.model.AlertPriority;
import com.abhilash.fleetmanagement.model.Reading;
import com.abhilash.fleetmanagement.model.Tires;
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
@Rule(name = "Incorrect Tyre pressure Rule", description = "Rule: tyre pressure of any tyre < 32 PSI or > 36 PSI, Priority: Low", priority = 2)
public class IncorrectTyrePressureRule {
    private AlertRepo alertRepo;

    @Condition
    public boolean isAlert(@Fact(value = "reading") Reading reading) {
        Tires tyres = reading.getTires();
        return tyres.getFrontLeft() < 32 || tyres.getFrontLeft() > 36
                || tyres.getFrontRight() < 32 || tyres.getFrontRight() > 36
                || tyres.getRearLeft() < 32 || tyres.getRearLeft() > 36
                || tyres.getRearRight() < 32 || tyres.getRearRight() > 36;
    }

    @Action
    public void addAlert(@Fact(value = "reading") Reading reading) {
        alertRepo.save(Alert.builder()
                .readingId(reading.getId())
                .timestamp(reading.getTimestamp())
                .priority(AlertPriority.LOW)
                .vin(reading.getVin())
                .description("Rule: tyre pressure of any tyre < 32 PSI or > 36 PSI")
                .build());
    }
}
