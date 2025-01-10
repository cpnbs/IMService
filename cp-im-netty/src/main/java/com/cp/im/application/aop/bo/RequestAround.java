package com.cp.im.application.aop.bo;

import lombok.Data;

/**
 * 请求信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/5/19 15:05
 */

@Data
public class RequestAround {

  /**
   * 请求IP.
   */
  private String ip;

  /**
   * 请求地址.
   */
  private String aUrl;

  /**
   * 请求设备.
   */
  private String device;

  /**
   * 请求版本.
   */
  private String version;

  /**
   * 版本拓展.
   */
  private String versionExtra;

  /**
   * 请求方法.
   */
  private String httpMethod;

  /**
   * 请求类方法.
   */
  private String classMethod;

  /**
   * 请求参数.
   */
  private Object requestParams;

  /**
   * 请求结果.
   */
  private Object result;

  /**
   * 请求耗时.
   */
  private Long timeCost;

}
