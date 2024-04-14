package com.islamov.tracktimer.springbootstarter.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Info {

    /**
     * Название метода
     */
    private String nameMethod;

    /**
     * Аргументы метода
     */
    private String arguments;

    /**
     * Время выполнения метода, мс
     */
    private Long executionTime;

    /**
     * Среднее время выполнения метода
     */
    private Long averageExecutionTime;

    /**
     * Дата и время запроса информации по методу
     */
    private LocalDateTime requestDate;
}
