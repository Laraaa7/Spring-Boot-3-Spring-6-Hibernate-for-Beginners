package com.lara.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // aqui es donde se añaden todas nuestras advertencias relacionadas para el logging

    // empezamos con un advertencia @Before - ejecuta el metodo ANTES que addAccount()

   // @Before("execution(public void add*())")
   // @Before("execution(* add*(com.lara.aopdemo.Account))")
   // @Before("execution(* add*(com.lara.aopdemo.Account, ..))")
   // @Before("execution(* com.lara..add*(..))")

    @Before("execution(* com.lara.aopdemo.dao.*.*(..))")
   public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on method");
    }
}
