package com.islamov.tracktimer.springbootstarter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private Object result;
    private long executionTime;
    private String methodName;
    private String arguments;
}
