package com.islamov.tracktimer.springbootstarter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.islamov.tracktimer.springbootstarter.dto.Info;
import com.islamov.tracktimer.springbootstarter.entity.ExecutionTime;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class InfoService {

    private final ExecutionTimeService executionTimeService;


    public InfoService(ExecutionTimeService executionTimeService) {
        this.executionTimeService = executionTimeService;
    }

    public Info getInfo(String nameMethod) throws IOException {
        ExecutionTime executionTime = executionTimeService.getExecutionTime(nameMethod);
        Info info = new ObjectMapper().readValue(executionTime.toString().getBytes(), Info.class);
        info.setRequestDate(LocalDateTime.now());
        return info;
    }
}
