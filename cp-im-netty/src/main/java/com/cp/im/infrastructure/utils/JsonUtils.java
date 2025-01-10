package com.cp.im.infrastructure.utils;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Json工具.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/4/2 14:55
 */

public class JsonUtils {

  /**
   * 将object转换成 Map<String, Object>.
   *
   * @param object 数据对象
   * @return map
   */
  public static Map<String, Object> toObjectMap(Object object) {
    String jsonObjString = JSONObject.toJSONString(object);
    return JSONObject.parseObject(jsonObjString);
  }

  /**
   * 将object转换成 List<Map<String, Object>>.
   *
   * @param object 数据对象
   * @return list
   */
  public static List<Map<String, Object>> toListMap(Object object) {
    String jsonObjString = JSONObject.toJSONString(object);
    List<Object> list = JSONObject.parseArray(jsonObjString);
    return list.stream().map(JSONObject::toJSONString)
        .map(JSONObject::parseObject).collect(Collectors.toList());
  }

  /**
   * 将object转换成 List<Type>.
   *
   * @param object 数据对象
   * @param beanType 对象类型
   * @param <T> 类型
   * @return list
   */
  public static <T> List<T> toListType(Object object, Class<T> beanType) {
    String jsonObjString = JSONObject.toJSONString(object);
    return JSONObject.parseArray(jsonObjString, beanType);
  }

  /**
   * 将object转换成 pojo.
   *
   * @param object 数据对象
   * @param beanType 对象类型
   * @param <T> 类型
   * @return model
   */
  public static <T> T toObjectPojo(Object object, Class<T> beanType) {
    String jsonObjString = JSONObject.toJSONString(object);
    return JSONObject.parseObject(jsonObjString, beanType);
  }

}
