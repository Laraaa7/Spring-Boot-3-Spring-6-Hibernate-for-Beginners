package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // hacer declaraciones pointcut
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {

    }
    // hacer lo mismo para service y dao

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forServicePackage() {

    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forDaoPackage() {

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    // añadir @Before
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        // mostrar metodo que estamos llamando
        String theMethod =  theJoinPoint.getSignature().toShortString();
        myLogger.info("========>>> in @Before: calling method: " + theMethod);

        // mostrar los argumentos al metodo
        // obtener los argumentos
        Object[] args = theJoinPoint.getArgs();

        // recorrerlos y mostrarlos
        for (Object tempArg : args) {
            myLogger.info("=========>>> argument: " + tempArg);
        }
    }

    // añadir aviso @AfterReturning
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        // mostrar metodo del que volvemos
        String theMethod =  theJoinPoint.getSignature().toShortString();
        myLogger.info("========>>> in @AfterReturning: calling method: " + theMethod);

        //mostrar datos devueltos
        myLogger.info("========>>> result: " + theResult);
    }
}

