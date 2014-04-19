package com.xhh.demo.http.practice.aop.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by 夏海虎 on 2014/4/9.
 */
@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.xhh.demo.http.practice.aop.spring.BookServiceImpl.*(..))")
    private void anyMethod(){}

    @Before("anyMethod() && args(name)")
    public void doAccessCheck(String name){
        System.out.println(name);
        System.out.println("Before 前置通知");
    }

    @AfterReturning("anyMethod()")
    public void doAfter(){
        System.out.println("AfterReturning 后置通知");
    }

    @After("anyMethod()")
    public void after(){
        System.out.println("After 最终通知");
    }

    @AfterThrowing("anyMethod()")
    public void doAfterThrow(){
        System.out.println("AfterThrowing 例外通知");
    }

    @Around("anyMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("Around 进入环绕通知");
        Object object = pjp.proceed();//执行该方法
        System.out.println("Around 退出方法");
        return object;
    }
}
