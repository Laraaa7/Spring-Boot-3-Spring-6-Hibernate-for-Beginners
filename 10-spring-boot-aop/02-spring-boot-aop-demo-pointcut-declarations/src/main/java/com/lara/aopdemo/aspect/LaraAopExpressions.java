package com.lara.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LaraAopExpressions {

    @Pointcut("execution(* com.lara.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    // crear un pointcut para metodos getter
    @Pointcut("execution(* com.lara.aopdemo.dao.*.get*(..))")
    public void getter() {}

    // crear un pointcut para metodos setter
    @Pointcut("execution(* com.lara.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // crear un pointcut para incluir packages y excluir getters y setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

}
