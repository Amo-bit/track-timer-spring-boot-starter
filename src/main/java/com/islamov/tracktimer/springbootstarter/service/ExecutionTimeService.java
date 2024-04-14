package com.islamov.tracktimer.springbootstarter.service;

import com.islamov.tracktimer.springbootstarter.entity.ExecutionTime;
import com.islamov.tracktimer.springbootstarter.repository.ExecutionTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    public long getAverageExecutionTime(String methodName){
        if(executionTimeRepository.getAverageExecutionTime(methodName) != null){
            return executionTimeRepository.getAverageExecutionTime(methodName);
        }else{
            return 0;
        }
    }

    public long getLastExecutionTime(String methodName){
        if(executionTimeRepository.getAverageExecutionTime(methodName) != null){
            return executionTimeRepository.getAverageExecutionTime(methodName);
        }else{
            return 0;
        }
    }

    public void save(ExecutionTime executionTime){
        executionTimeRepository.save(executionTime);
    }
}
