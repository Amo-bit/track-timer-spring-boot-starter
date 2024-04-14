package com.islamov.tracktimer.springbootstarter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "execution_time_list")
@Entity
public class ExecutionTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_method")
    private String nameMethod;

    @Column(name ="arguments")
    private String arguments;

    @Column(name = "execution_time")
    private long executionTime;

    @Column(name = "average_execution_time")
    private long averageExecutionTime;
}
