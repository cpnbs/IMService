package com.cp.im.infrastructure.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 处理注解.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/1/23 16:53
 */

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleTag {

  /** 标签编码. */
  int tag();

}
