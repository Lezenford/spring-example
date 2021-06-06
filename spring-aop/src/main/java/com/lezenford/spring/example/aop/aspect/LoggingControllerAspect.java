package com.lezenford.spring.example.aop.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Аннотация {@link Aspect} необходима для работы данного класса
 */
@Aspect
@Component
public class LoggingControllerAspect {

    private static final Logger log = LogManager.getLogger(LoggingControllerAspect.class);

    /**
     * Указываем срез точек, для которых будет использовать аспект. В данном случае мы говорим, что это любые методы в любых классах, лежащих в
     * пакете com.lezenford.spring.example.aop.controller
     * Для одного аспекта может быть несколько разных срезов точек
     */
    @Pointcut("execution(* com.lezenford.spring.example.aop.controller.*.*(..))")
    public void pointcut() {
    }

    /**
     * Данный код выполняется до и после вызова метода контроллера
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Around aspect. Before method invoke");
        final Object result = pjp.proceed();
        log.info("Around aspect. Afted method invoke");
        return result;
    }

    /**
     * Данный код вызывается только до метода контроллера
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before aspect");
    }

    /**
     * Данный код вызывается только после метода контроллера
     */
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        log.info("After aspect");
    }

    /**
     * Данный код выполняется только после успешного завершения метода контроллера
     */
    @AfterReturning(value = "pointcut()", returning = "obj")
    public void afterReturning(Object obj) {
        log.info("After returning aspect. Result: " + obj);
    }

    /**
     * Данный код выполняется успешно только если метод контроллера бросил исключение
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Throwable e) {
        log.info("After throw aspect. Error: " + e.getClass().getName());
    }
}
