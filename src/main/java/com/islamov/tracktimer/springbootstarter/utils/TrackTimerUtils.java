package com.islamov.tracktimer.springbootstarter.utils;

import com.islamov.tracktimer.springbootstarter.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public class TrackTimerUtils {

    private TrackTimerUtils(){

    }

    public static Result getExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] methodArgs = proceedingJoinPoint.getArgs();

        log.info("Выполняется метод {} с аргументами {}", methodName, methodArgs);

        Object result = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;

        log.info("Метод {} выполняется за {} мс", methodName, executionTime);

        return new Result(result, executionTime);
    }
}
