package com.islamov.tracktimer.springbootstarter.aspect;

import com.islamov.tracktimer.springbootstarter.model.Result;
import com.islamov.tracktimer.springbootstarter.utils.TrackTimerUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TrackTimeAspect {

    @Pointcut("@annotation(com.islamov.tracktimer.springbootstarter.annotation.TrackTime)")
    public void trackTimePointcut(){}

    @Around("trackTimePointcut()")
    public Object trackTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Result result = TrackTimerUtils.getExecutionTime(proceedingJoinPoint);

        return result.getResult();
    }
}
