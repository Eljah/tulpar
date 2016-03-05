package com.github.eljah.tulpar.aop;


import com.github.eljah.tulpar.annotation.RetryIfException;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ServiceAspect {

    public void before() {
        System.out.println("РАБОТАЕТ!!!");
    }

    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        //System.out.println("Start invoking "
        //        + joinPoint.getTarget().getClass().getSimpleName()
        //        + "."
        //        + joinPoint.getSignature().getName()
        //        + " with params "
        //        + Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        //System.out.println("End invoking method: " + (end - start) + "ms");
        return result;
    }

    public Object retry(ProceedingJoinPoint joinPoint) throws Throwable {
        Class clazz = joinPoint.getTarget().getClass();
        Method method = clazz.getMethod(joinPoint.getSignature().getName());
        RetryIfException retryIfException = method.getAnnotation(RetryIfException.class);
        int count = retryIfException.value();
        System.out.println("OK BOSS "+count);
        while (--count>0){
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                System.out.println("Ошибка, еще "+(count)+" попыток");
            }
        }
        return joinPoint.proceed();
    }

}
