package com.cp.im.infrastructure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 重复提交验证.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/4/10 15:52
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Resubmit {

  int time() default 5;
}
