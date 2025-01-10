package com.cp.im.infrastructure.factory;

import com.cp.im.infrastructure.annotation.ModuleTag;
import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * 工厂处理方法.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/5/11 14:33
 */

@Component
public class HandlerFactory implements BeanFactoryPostProcessor {

  private static final String HANDLER_PACKAGE = "com.cp.im.module";

  @Override
  public void postProcessBeanFactory(@NonNull ConfigurableListableBeanFactory beanFactory) throws BeansException {
    Map<Integer, Class<?>> handlerMap = Maps.newHashMapWithExpectedSize(3);
    ClassScanner.scan(HANDLER_PACKAGE, ModuleTag.class).forEach(clazz -> {
      int code = clazz.getAnnotation(ModuleTag.class).tag();
      handlerMap.put(code, clazz);
    });
    HandlerContext context = new HandlerContext(handlerMap);
    beanFactory.registerSingleton(HandlerContext.class.getName(), context);
  }

}
