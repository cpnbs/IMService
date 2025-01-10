package com.cp.im.utils;

/**
 * 字符工具.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/6/21 14:49
 */

public class CharUtil {

  private CharUtil(){}

  /**
   * 是否为空.
   *
   * @param character 字符
   * @return boolean 是/否
   * @author wangcaiwen
   * @since 2021/6/21 16:51
   */
  public static boolean isEmpty(final Character character) {
    return character == null;
  }

  /**
   * 是否不为空.
   *
   * @param character 字符
   * @return boolean 是否
   * @author wangcaiwen
   * @since 2021/6/21 16:51
   */
  public static boolean isNotEmpty(final Character character) {
    return !isEmpty(character);
  }

  /**
   * 重复多次.
   *
   * @param unit 单元信息
   * @param times 次数
   * @return 重复结果
   * @author wangcaiwen
   * @since 2021/6/21 16:52
   */
  public static String repeat(final char unit, final int times) {
    String component = String.valueOf(unit);
    return StringUtils.repeat(component, times);
  }

  /**
   * 将字符串中的全角字符转为半角.
   * <p>
   * 全角空格为12288，半角空格为32
   * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248</p>
   *
   * @param c 原始字符
   * @return 转换之后的字符
   * @author wangcaiwen
   * @since 2021/6/21 16:52
   */
  public static char toHalfWidth(final char c) {
    char resultChar = c;
    int flag = 32;
    // 全角空格
    int flag1 = 12288;
    int flag2 = 65248;
    int flag3 = 65280;
    int flag4 = 65375;
    if (resultChar == flag1) {
      resultChar = (char) flag;
      // 其他全角字符
    } else if (resultChar > flag3 && resultChar < flag4) {
      resultChar = (char) (resultChar - flag2);
    }
    return resultChar;
  }

  /**
   * 将半角字符转为全角.
   * <p>
   * 全角空格为12288，半角空格为32
   * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248</p>
   *
   * @param c 原始字符
   * @return 转换之后的字符
   * @author wangcaiwen
   * @since 2021/6/21 16:52
   */
  public static char toFullWidth(final char c) {
    char resultChar = c;
    // 半角空格
    int flag = 32;
    int flag1 = 33;
    int flag2 = 126;
    int flag3 = 12288;
    int flag4 = 65248;
    if (resultChar == flag) {
      resultChar = (char) flag3;
      // 其他半角字符
    } else if (resultChar >= flag1 && resultChar <= flag2) {
      resultChar = (char) (resultChar + flag4);
    }
    return resultChar;
  }

  /**
   * 使用UnicodeScript方法判断是否为中文字符.
   *
   * @param c 编码
   * @return 是否为中文标点符号
   * @author wangcaiwen
   * @since 2021/6/21 16:52
   */
  public static boolean isChinesePunctuation(char c) {
    Character.UnicodeScript sc = Character.UnicodeScript.of(c);
    return sc == Character.UnicodeScript.HAN;
  }

  /**
   * 是否为空格.
   *
   * @param c 编码
   * @return 是否为空格
   * @author wangcaiwen
   * @since 2021/6/21 16:52
   */
  public static boolean isSpace(final char c) {
    return Character.isSpaceChar(c) || '\u0013' == c;
  }
}
