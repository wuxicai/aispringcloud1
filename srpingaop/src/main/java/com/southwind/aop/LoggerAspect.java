package com.southwind.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
@Aspect//该类是切面类
@Slf4j
public class LoggerAspect {
    @Before(value = "execution(public int com.southwind.utils.impl.CalImpl.*(..))")//执行位置和时机
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();

        String s = Arrays.toString(joinPoint.getArgs());
        log.info(name+s);
/** System.out.println(name+"方法的参数是："+s);*/
    }
    @After(value = "execution(public int com.southwind.utils.impl.CalImpl.*(..))")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法执行完毕");
    }
    @AfterReturning(value = "execution(public int com.southwind.utils.impl.CalImpl.*(..))",returning = "result")
    public void afterR(JoinPoint joinPoint,Object result){
        String name = joinPoint.getSignature().getName();

        System.out.println(name+"方法的结果是："+result);
    }
    @AfterThrowing(value = "execution(public int com.southwind.utils.impl.CalImpl.*(..))",throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint,Exception exception){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法抛出异常："+exception);
    }
}
