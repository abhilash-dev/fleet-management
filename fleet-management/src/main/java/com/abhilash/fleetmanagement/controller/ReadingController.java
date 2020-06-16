package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.ReadingDao;
import com.abhilash.fleetmanagement.service.AlertService;
import com.abhilash.fleetmanagement.service.ReadingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://mocker.egen.academy"}, maxAge = 600, methods = {RequestMethod.OPTIONS, RequestMethod.POST})
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "/readings")
public class ReadingController {

    private final ReadingService readingService;
    private final AlertService alertService;

    @PostMapping
    public ResponseEntity addReadings(@RequestBody ReadingDao readingDao) {
        long id = readingService.addReading(readingDao);
        alertService.check(id);
        return ResponseEntity.ok().build();
    }
}
