package com.cp.im.utils;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ip工具.
 *
 * @author xxxxxxxxxx
 * @version v1.0.0
 * @since 2021/8/5 12:52
 */

public class IpUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(IpUtil.class);

  private static final String UNKNOWN = "unknown";
  private static final String SEPARATOR = ",";
  private static final String LOCALHOST_IP = "0:0:0:0:0:0:0:1";
  private static final String LOCALHOST_IP1 = "127.0.0.1";

  public static String getIpAddress(HttpServletRequest request) {
    String ipAddress;
    try {
      ipAddress = request.getHeader("X-Original-Forwarded-For");
      if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("X-Forwarded-For");
      }
      if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("x-forwarded-for");
      }
      if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("Proxy-Client-IP");
      }
      if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("WL-Proxy-Client-IP");
      }
      if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("HTTP_CLIENT_IP");
      }
      if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
        ipAddress = request.getRemoteAddr();
        if (LOCALHOST_IP.equals(ipAddress) || LOCALHOST_IP1.equals(ipAddress)) {
          InetAddress iNet = null;
          try {
            iNet = InetAddress.getLocalHost();
          } catch (UnknownHostException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(ExceptionUtil.getStackTrace(e));
          }
          ipAddress = Objects.requireNonNull(iNet).getHostAddress();
        }
      }
      if (StringUtils.isNotEmpty(ipAddress) && ipAddress.indexOf(SEPARATOR) > 0) {
          ipAddress = ipAddress.substring(0, ipAddress.indexOf(SEPARATOR));
      }
    } catch (Exception e) {
      ipAddress = "";
    }
    return ipAddress;
  }

}
