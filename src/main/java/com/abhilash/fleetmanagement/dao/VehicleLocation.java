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
@ApiModel(value = "VehicleLocation", description = "Class representing the vehicle's location as tracked by the sensor(mock)")
public class VehicleLocation {
    @ApiModelProperty(notes = "Latitude value of the truck's location", example = "41.803194", required = true, position = 0)
    private double latitude;
    @ApiModelProperty(notes = "Longitude value of the truck's location", example = "-88.144406", required = true, position = 1)
    private double longitude;
    @ApiModelProperty(notes = "The time at which the location was captured", example = "2019-02-10T03:04:33Z", required = true, position = 2)
    private Instant timestamp;
}
