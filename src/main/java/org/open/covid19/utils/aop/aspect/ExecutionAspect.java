package org.open.covid19.utils.aop.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * execution表达式
 * @author wuchao
 */
@Aspect
@Component
@Slf4j
public class ExecutionAspect {
    /**
     * 仅针对某一个方法,execution表达式
     */
    @Pointcut("execution(public * org.open.covid19.service.impl.LoggerApply .requestLastAmericanCases(..) ))")
    private void cutMethod(){ }

    @SneakyThrows
    @Around("cutMethod()")
    public Object around(ProceedingJoinPoint joinPoint){
        System.out.println("around");
        Object retVal = joinPoint.proceed(Arrays.asList("参数1","参数2").toArray());
        log.debug("around-执行方法的返回值:{}",retVal);
        return "222";
    }



    /**
     * `returning`接收源方法返回值。如果`Around`方法改变了源方法的入参，`retVal`就会变为null（不清楚什么原因）
     * @param retVal
     */
    @AfterReturning(
            pointcut="cutMethod()",
            returning="retVal"
    )
    public void doAccessAfter(Object retVal) {
        log.debug("doAccessAfter源方法的返回值:{}",retVal);
    }


}
