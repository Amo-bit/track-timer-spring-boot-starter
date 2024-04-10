package com.islamov.tracktimer.springbootstarter.aspect;

import com.islamov.tracktimer.springbootstarter.model.Result;
import com.islamov.tracktimer.springbootstarter.utils.TrackTimerUtils;
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
public class TrackAsyncTimeAspect {

    @Pointcut("@annotation(com.islamov.tracktimer.springbootstarter.annotation.TrackAsyncTime))")
    public void trackAsyncTimePointcut(){}

    @Around("trackAsyncTimePointcut()")
    public Object trackAsyncTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return CompletableFuture.runAsync(() -> {
            try {
                log.info("run TrackAsyncTimeAspect");
                Result result = TrackTimerUtils.getExecutionTime(proceedingJoinPoint);
            } catch (Throwable e) {
                log.error("error TrackAsyncTimeAspect", e);
            }
        });

    }
}
