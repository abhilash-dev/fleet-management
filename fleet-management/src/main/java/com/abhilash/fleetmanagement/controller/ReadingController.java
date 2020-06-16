package com.abhilash.fleetmanagement.controller;

import com.abhilash.fleetmanagement.dao.ReadingDao;
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

    @PostMapping
    public ResponseEntity addReadings(@RequestBody ReadingDao readingDao) {
        readingService.addReading(readingDao);
        return ResponseEntity.ok().build();
    }
}
