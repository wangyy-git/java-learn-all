package com.wangyy.ltd.springmvclearn.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WController {
    String value() default "";
}
