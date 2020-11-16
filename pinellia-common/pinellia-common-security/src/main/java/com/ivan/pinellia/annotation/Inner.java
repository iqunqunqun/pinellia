package com.ivan.pinellia.annotation;

import java.lang.annotation.*;

/**
 * <p>服务调用不鉴权注解</p>
 *
 * @author ivan
 * @className Inner
 * @since 2020/11/15 0:02
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

    /**
     * 是否AOP统一处理
     * @return false, true
     */
    boolean value() default true;

    /**
     * 需要特殊判空的字段(预留)
     * @return {}
     */
    String[] field() default {};

}

