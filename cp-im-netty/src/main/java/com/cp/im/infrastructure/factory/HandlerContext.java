package com.cp.im.infrastructure.factory;

import com.cp.im.infrastructure.OptionalHandler;
import java.util.Map;
import java.util.Objects;

/**
 * 处理上下文.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/5/11 16:23
 */

public class HandlerContext {

  private final Map<Integer, Class<?>> HANDLER_MAP;

  public HandlerContext(Map<Integer, Class<?>> handlerMap) {
    this.HANDLER_MAP = handlerMap;
  }

  public OptionalHandler getInstance(Integer cmd) {
    Class<?> aClass = HANDLER_MAP.get(cmd);
    assert !Objects.isNull(aClass) : "get instance failed: [" + cmd + "]";
    return (OptionalHandler) BeanTool.getBean(aClass);
  }

}
