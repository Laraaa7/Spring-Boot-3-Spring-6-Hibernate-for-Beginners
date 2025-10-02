package com.lara.aopdemo.aspect;

import com.lara.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.lara.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        //imprimir metodo que avisamos
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @Around method: " + method);

        // comenzar el tiempo
        // long begin = System.currentTimeMillis();

        long begin = System.nanoTime();
        // ejecutar metodo
        Object result = null;

        try{
            result = theProceedingJoinPoint.proceed();
        }catch(Exception exc){

            //registrar excepcion
            System.out.println(exc.getMessage());

           // volver a lanzar excepcion
            throw exc;
        }

        // finalizar tiempo
        // long end = System.currentTimeMillis();
        long end = System.nanoTime();

        // computar duracion y mostrarla
        long duration = end - begin;
        System.out.println("\n=====>>> Duration: " + duration + " nanoseconds");

        return result;
    }

    @After("execution(* com.lara.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyAccountsAdvice(JoinPoint theJoinPoint) {

        // imprimir que metodo vamos a hacer el aviso
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @After (finally) method: " + method);

    }

    @AfterThrowing(
            pointcut = "execution(* com.lara.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc) {

        // imprimir que metodo vamos a hacer el aviso
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @AfterThrowing method: " + method);

        // registrar la excepcion
        System.out.println("\n======>>> The exception is: " + theExc);
    }

    // añadir un nuevo aviso para el metodo @AfterReturning
    @AfterReturning(
            pointcut = "execution(* com.lara.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // imprimir que metodo vamos a hacer el aviso
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n======>>> Executing @AfterReturning method: " + method);

        // imprimir resultados de la llamada del metodo
        System.out.println("\n======>>> Result is: " + result);

        // post-procesar los datos y modificarlos
        // convertir los nombres de la cuenta a mayuscula
        convertAccountNamesToUpperCase(result);
        System.out.println("\n======>>> Result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // recorrer las cuentas
        for (Account tempAccount : result) {

            // pasar a mayuscula
            String theUpperName = tempAccount.getName().toUpperCase();

            // actualizar el nombre en la cuenta
            tempAccount.setName(theUpperName);
        }

    }

    @Before("com.lara.aopdemo.aspect.LaraAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>> Executing @Before advice on method");

    // mostrar signature del metodo
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

    //mostrar argumentos metodo
    // obtener args
        Object[] args = theJoinPoint.getArgs();

    // recorrer args e imprimirlos
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account) {

                // bajar e imprimir cosas especificas de account
                Account theAccount = (Account) tempArg;
                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());
            }
        }
    }

}

