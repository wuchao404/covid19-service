package org.open.covid19.utils.aop.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.open.covid19.utils.aop.annotation.HttpCustom;
import org.open.covid19.utils.http.HttpClientUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 高仿FeignClient,做一个切面
 * @author wuchao
 */
@Component
@Aspect
@Slf4j
public class FeignClientAspect {
    @Pointcut("@within(org.open.covid19.utils.aop.annotation.HttpCustom) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void cutMethod(){}

    @Around("cutMethod()")
    public String around(ProceedingJoinPoint joinPoint){
        HttpCustom httpCustom = getClassAnnotation(joinPoint, HttpCustom.class);
        RequestMapping requestMapping = getMethodAnnotation(joinPoint, RequestMapping.class);
        log.debug("路由：{}",requestMapping.value()[0]);
        String url = httpCustom.url() + requestMapping.value()[0];
        RequestMethod method = requestMapping.method()[0];
        String result = "";
        if (method == RequestMethod.GET) {
            result = HttpClientUtils.sendHttpGet(url);
        }
        return result;
    }

    /**
     * 获取方法的注解信息
     * @return
     */
    @SneakyThrows
    private <T extends Annotation> T getMethodAnnotation(ProceedingJoinPoint joinPoint, Class<T> tClass) {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        return objMethod.getDeclaredAnnotation(tClass);
    }

    /**
     * 获取类的注解信息
     * @return
     */
    @SneakyThrows
    private <T extends Annotation> T getClassAnnotation(ProceedingJoinPoint joinPoint, Class<T> tClass) {
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        return targetClass.getAnnotation(tClass);
    }

}
