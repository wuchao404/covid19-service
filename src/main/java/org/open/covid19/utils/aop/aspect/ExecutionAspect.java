package org.open.covid19.utils.aop.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * execution表达式
 * @author wuchao
 */
@Aspect
@Component
public class ExecutionAspect {
    /**
     * 仅针对某一个方法,execution表达式
     */
    @Pointcut("execution(public * org.open.covid19.service.impl.LoggerApply .requestLastAmericanCases(..) ))")
    private void cutMethod(){}

    @SneakyThrows
    @Around("cutMethod()")
    public void around(ProceedingJoinPoint joinPoint){
        System.out.println("around");
        joinPoint.proceed();
    }
}
