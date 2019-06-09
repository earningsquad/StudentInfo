package com.dev.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//定义注解使用于参数上
@Target(ElementType.METHOD)
//定义注解在运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
}
