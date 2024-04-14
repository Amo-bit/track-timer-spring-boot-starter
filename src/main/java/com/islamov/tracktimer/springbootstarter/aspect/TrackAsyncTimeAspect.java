package com.islamov.tracktimer.springbootstarter.aspect;

import com.islamov.tracktimer.springbootstarter.entity.ExecutionTime;
import com.islamov.tracktimer.springbootstarter.model.Result;
import com.islamov.tracktimer.springbootstarter.service.ExecutionTimeService;
import com.islamov.tracktimer.springbootstarter.utils.TrackTimerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class TrackAsyncTimeAspect {

    private final ExecutionTimeService executionTimeService;

    @Pointcut("@annotation(com.islamov.tracktimer.springbootstarter.annotation.TrackAsyncTime))")
    public void trackAsyncTimePointcut(){}

    @Around("trackAsyncTimePointcut()")
    public Object trackAsyncTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return CompletableFuture.runAsync(() -> {
            try {
                log.info("run TrackAsyncTimeAspect");
                Result result = TrackTimerUtils.getExecutionTime(proceedingJoinPoint);

                //create and save entity
                ExecutionTime executionTime = new ExecutionTime();
                executionTime.setNameMethod(result.getMethodName());
                executionTime.setArguments(result.getArguments());
                executionTime.setExecutionTime(result.getExecutionTime());
                //get average execution time for method
                long averageTime = executionTimeService.getAverageExecutionTime(executionTime.getNameMethod());

                if(averageTime != 0){
                    executionTime.setAverageExecutionTime((averageTime + executionTime.getExecutionTime()) / 2);
                }else{
                    executionTime.setAverageExecutionTime(executionTime.getExecutionTime());
                }
                executionTimeService.save(executionTime);
            } catch (Throwable e) {
                log.error("error TrackAsyncTimeAspect", e);
            }
        });

    }
}
