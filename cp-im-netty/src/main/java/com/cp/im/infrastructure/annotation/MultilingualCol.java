package com.cp.im.infrastructure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识要处理多语言的方法
 * 使用方法，在mapper中需要多语言的方法上增加本注解，并标识要对语言的字段和别名
 * @author 暮雪超霸
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MultilingualCol {

    /**
     * 要多语言的字段名称，如果有别名 则需要带上别名
     * 正常：name,nick
     * 带有别名 s1.name,s2.nick
     * @return
     */
    String[] colNames() default {};

    /**
     * 原生sql中是否有别名，如果有别名，需要写上别名  如name as name1,则此次需要写上name1
     * 如果对应字段没别名，则留空，写入“”即可
     *
     * @return
     */
    String[] aliasNames() default {};

}
