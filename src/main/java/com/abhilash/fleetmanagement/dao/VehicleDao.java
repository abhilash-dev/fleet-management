package com.abhilash.fleetmanagement.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Vehicle", description = "Class representing a Vehicle")
public class VehicleDao {
    @ApiModelProperty(notes = "Vehicle Identifier (unique)", example = "1FMYU02143KB34432", required = true, position = 0)
    private String vin;
    @ApiModelProperty(notes = "vehicle's make", example = "Honda", required = true, position = 1)
    private String make;
    @ApiModelProperty(notes = "vehicle's model", example = "Accord", required = true, position = 2)
    private String model;
    @ApiModelProperty(notes = "vehicle's release year", example = "2015", required = true, position = 3)
    private int year;
    @ApiModelProperty(notes = "vehicle's redline engine RPM", example = "6300", required = true, position = 4)
    private int redlineRpm;
    @ApiModelProperty(notes = "vehicle's max fuel volume", example = "18", required = true, position = 5)
    private int maxFuelVolume;
    @ApiModelProperty(notes = "The date & time the last time vehicle was serviced", example = "2019-02-10T03:04:33Z", required = true, position = 6)
    private Instant lastServiceDate;
}
