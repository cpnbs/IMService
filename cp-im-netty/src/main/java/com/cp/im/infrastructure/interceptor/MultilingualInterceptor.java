package com.cp.im.infrastructure.interceptor;

import com.cp.im.infrastructure.annotation.MultilingualCol;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Properties;

/**
 * 处理多语言字段，将需要处理多语言的字段和多语言码拼接。
 * 如默认字段name  多语言字段name_en 本程序将会根据多语言，将原生name字段变成name_en as name
 * @author 暮雪超霸
 */
@Component
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
})
public class MultilingualInterceptor implements Interceptor {

    private static final String DefaultLanguage = "cn";

    public static final String SELECT = "select";

    public static final String FROM = "from";


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("执行了");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        BoundSql boundSql = statementHandler.getBoundSql();
        String originSql = boundSql.getSql();
        if (!StringUtils.isEmpty(originSql)) {
            MultilingualCol multilingualCol = getMultilingualCol(mappedStatement);
            if (multilingualCol != null) {
                String[] colNames = multilingualCol.colNames();
                String[] aliasNames = multilingualCol.aliasNames();
                String language = "en"; //ServletUtils.getHeader("Accept-Language", DefaultLanguage);
                System.out.println("需要转换的语言标识："+language);
                if (!language.equals(DefaultLanguage)) {
                    int start = originSql.indexOf(SELECT);
                    if (start == -1) {
                        start = originSql.indexOf(SELECT.toUpperCase());
                    }
                    int end = originSql.indexOf(FROM);
                    if (end == -1) {
                        end = originSql.indexOf(FROM.toUpperCase());
                    }
                    if (start != -1 && end != -1) {
                        System.out.println(originSql);
                        System.out.println(start);
                        System.out.println(end);
                        System.out.println(SELECT.length() + start);
                        String colNameStr = originSql.substring(SELECT.length() + start, end);
                        String startSQL = originSql.substring(0, SELECT.length() + start);
                        String endSQL = originSql.substring(end);

                        colNameStr = colNameStr.trim();
                        String[] colNameSplit = colNameStr.split(",");
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < colNameSplit.length; i++) {
                            String col = colNameSplit[i].trim();
                            for (int j = 0; j < colNames.length; j++) {
                                String name = colNames[j];
                                if (col.equals(name)) {
                                    if (col.indexOf(".") == -1) {
                                        col = col + "_" + language + " as " + col;
                                    } else {
                                        String substring = col.substring(col.indexOf(".") + 1);
                                        col = col + "_" + language + " as " + substring;
                                    }
                                } else {
                                    if (!StringUtils.isBlank(aliasNames[j])) {
                                        if (col.contains(name) && col.contains(aliasNames[j])) {
                                            col = col.replaceFirst(name, name + "_" + language);
                                        }
                                    }

                                }
                            }
                            if (i != colNameSplit.length - 1) {
                                sb.append(col).append(",");
                            } else {
                                sb.append(col);
                            }
                        }

                        String newSQL = startSQL + " " + sb.toString() + " " + endSQL;
                        metaObject.setValue("delegate.boundSql.sql", newSQL);
                        System.out.println(originSql);
                        System.out.println(newSQL);
                    }


                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return (target instanceof StatementHandler) ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {
    }


    public MultilingualCol getMultilingualCol(MappedStatement mappedStatement) {
        MultilingualCol multilingualCol = null;
        try {
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(id.lastIndexOf(".") + 1);
            final Method[] method = Class.forName(className).getMethods();
            for (Method me : method) {
                if (me.getName().equals(methodName) && me.isAnnotationPresent(MultilingualCol.class)) {
                    multilingualCol = me.getAnnotation(MultilingualCol.class);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return multilingualCol;
    }
}
