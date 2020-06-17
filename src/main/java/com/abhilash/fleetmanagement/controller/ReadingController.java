package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.ReadingDao;
import com.abhilash.fleetmanagement.service.AlertService;
import com.abhilash.fleetmanagement.service.ReadingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://mocker.egen.academy"}, maxAge = 600, methods = {RequestMethod.OPTIONS, RequestMethod.POST})
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/readings", produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(description = "Endpoints related to readings (mock) for Fleet-management")
public class ReadingController {

    private final ReadingService readingService;
    private final AlertService alertService;

    @ApiOperation(value = "Add new readings", httpMethod = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addReadings(@RequestBody ReadingDao readingDao) {
        long id = readingService.addReading(readingDao);
        alertService.check(id);
        return ResponseEntity.ok().build();
    }
}
