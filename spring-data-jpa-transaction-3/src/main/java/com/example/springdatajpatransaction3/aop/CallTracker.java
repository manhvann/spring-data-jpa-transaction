package com.example.springdatajpatransaction3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class CallTracker {
    @Pointcut("execution(* com.example.springdatajpatransaction3.repo.ProductRepository.updateById(..))")
    public void updateByIdPointCut() {
    }

    @Around("updateByIdPointCut()")
    public Object logAroundUpdateByIdCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // Before the method execution
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println("Method start: " + methodName);

        // Proceed with the method call
        Object returnValue = proceedingJoinPoint.proceed();
        System.out.println(returnValue);

        // After the method execution
        System.out.println("Method completed: " + methodName);
        return returnValue;
    }
}
