package com.liminghua.api.configuration.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检查接口开关注解
 *
 * @ClassName RequestCheck
 * @Description: 请求检验注解
 * @Author LiMinghua
 * @Date 2020/9/17
 * @Version V1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestCheck {
    String value() default "RequestChecking";
}
