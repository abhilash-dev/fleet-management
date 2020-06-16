package com.abhilash.fleetmanagement.config;

import com.abhilash.fleetmanagement.rules.FaultyEngineProblemRule;
import com.abhilash.fleetmanagement.rules.IncorrectRpmRule;
import com.abhilash.fleetmanagement.rules.IncorrectTyrePressureRule;
import com.abhilash.fleetmanagement.rules.LowFuelVolumeRule;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RulesConfig {

    @Bean
    public Rules getRules(FaultyEngineProblemRule faultyEngineProblemRule, IncorrectRpmRule incorrectRpmRule,
                          IncorrectTyrePressureRule incorrectTyrePressureRule, LowFuelVolumeRule lowFuelVolumeRule) {
        Rules rules = new Rules();
        rules.register(faultyEngineProblemRule);
        rules.register(incorrectRpmRule);
        rules.register(incorrectTyrePressureRule);
        rules.register(lowFuelVolumeRule);
        return rules;
    }

    @Bean
    public RulesEngine getRulesEngine() {
        // Set skipOnFirstAppliedRule = true when only the top priority rule has to be executed
        return new DefaultRulesEngine(new RulesEngineParameters().skipOnFirstAppliedRule(false));
    }
}
