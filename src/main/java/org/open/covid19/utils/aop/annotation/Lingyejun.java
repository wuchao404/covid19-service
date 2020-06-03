package org.open.covid19.utils.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Lingye
 * @Date: 2018/11/11
 * @Describe:
 * @Modified By:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lingyejun {

    /**
     * 何种场景下的通用日志打印
     *
     * @return
     */
    String module() default "";
}