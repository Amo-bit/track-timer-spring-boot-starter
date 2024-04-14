package com.islamov.tracktimer.springbootstarter.controller;

import com.islamov.tracktimer.springbootstarter.dto.Info;
import com.islamov.tracktimer.springbootstarter.dto.MethodInfo;
import com.islamov.tracktimer.springbootstarter.service.ExecutionTimeService;
import com.islamov.tracktimer.springbootstarter.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/track_timer")
public class InfoController {

    private final InfoService infoService;
    private final ExecutionTimeService executionTimeService;

    @PostMapping(value = "/info_last_method")
    public ResponseEntity<?> getInfoMethod(@RequestBody MethodInfo methodInfo){
        try {
            final Info info = infoService.getInfo(methodInfo.getName());
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/average_time")
    public ResponseEntity<Long> getAverageTime(@RequestBody MethodInfo methodInfo){
        return new ResponseEntity<>(executionTimeService.getAverageExecutionTime(methodInfo.getName()), HttpStatus.OK);
    }

    @PostMapping(value = "/last_time")
    public ResponseEntity<Long> getLastTime(@RequestBody MethodInfo methodInfo){
        return new ResponseEntity<>(executionTimeService.getLastExecutionTime(methodInfo.getName()), HttpStatus.OK);
    }
}
