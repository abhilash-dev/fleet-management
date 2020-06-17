package com.abhilash.fleetmanagement.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
@ApiModel(value = "Alert", description = "Class representing an Alert as tracked by the rules")
public class AlertDao {
    @ApiModelProperty(notes = "Priority of the Alert (0 - High, 1 - Medium & 2 - Low)", example = "0", required = true, position = 0)
    private int priority;
    @ApiModelProperty(notes = "Vehicle Identifier (unique)", example = "1FMYU02143KB34432", required = true, position = 1)
    private String vin;
    @ApiModelProperty(notes = "The time at which the alert was caused", example = "2019-02-10T03:04:33Z", required = true, position = 2)
    private Instant timestamp;
    @ApiModelProperty(notes = "The description of the alert", example = "Rule: engineCoolantLow = True || checkEngineLightOn = True", required = true, position = 3)
    private String description;
}
