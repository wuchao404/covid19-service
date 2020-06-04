package org.open.covid19.utils.aop.aspect;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.open.covid19.utils.aop.annotation.HttpRequestMapping;
import org.open.covid19.utils.http.HttpClientUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class HttpClientAspect {

    @Pointcut("@annotation(org.open.covid19.utils.aop.annotation.HttpRequestMapping)")
    private void cutMethod(){}


    @AfterThrowing("cutMethod()")
    public void afterThrow(){
        System.out.println("afterThrowing,异常结束");
    }
    @SneakyThrows
    @Around("cutMethod()")
    public Object around(ProceedingJoinPoint joinPoint){
        HttpRequestMapping httpRequestMapping = getAnnotation(joinPoint);
        String value = httpRequestMapping.value();
        String method = httpRequestMapping.method();
        String result = HttpClientUtils.sendHttpGet(value);

        return joinPoint.proceed(Arrays.asList(result).toArray());
    }

    /**
     * 获取注解信息
     * @return
     */
    @SneakyThrows
    private HttpRequestMapping getAnnotation(ProceedingJoinPoint joinPoint) {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        HttpRequestMapping annotation = objMethod.getDeclaredAnnotation(HttpRequestMapping.class);
        return annotation;
    }

}
