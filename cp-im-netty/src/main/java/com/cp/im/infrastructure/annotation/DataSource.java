package com.cp.im.infrastructure.annotation;

import com.cp.im.infrastructure.db.DataSourceType;

import java.lang.annotation.*;

/**
 * @ClassName: DataSource
 * @Description 数据源
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    /**
     * 切换数据源名称
     */
    DataSourceType value() default DataSourceType.CHAT;

}
