package com.cp.im.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/4/1 9:09
 */

public class ExceptionUtil {

  /**
   * 堆栈分析.
   *
   * @param e 异常信息
   * @return java.lang.String
   * @author wangcaiwen
   * @since 2020/4/1 9:09
   */
  public static String getStackTrace(Exception e) {
    StringWriter sw = new StringWriter();
    try (PrintWriter pw = new PrintWriter(sw)) {
      e.printStackTrace(pw);
      return sw.toString();
    }
  }
}
