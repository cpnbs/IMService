package com.cp.im.application.aop;

import com.alibaba.fastjson.JSON;
import com.cp.im.application.aop.bo.RequestAround;
import com.cp.im.application.aop.bo.RequestThrowing;
import com.cp.im.utils.IpUtil;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * 切面处理.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/11/3 10:52
 */

@Aspect
@Component
public class RequestLogAspect {

  private static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

  /**
   * 异常日志切入点.
   *
   * <p>设置操作异常切入点记录异常日志 扫描所有controller包下操作
   *
   * @author wangcaiwen
   * @since 2020/11/3 11:03
   */
  @Pointcut("execution(* com.cp.im.interfaces.facade..*.*(..))")
  public void requestPointCut() {
  }

  /**
   * 环绕通知.
   *
   * @param proceedingJoinPoint 切入点
   * @return java.lang.Object
   * @author xxxxxxxxxx
   * @since 2021/9/2 11:20
   */
  @Around("requestPointCut()")
  public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    Object requestResult = proceedingJoinPoint.proceed();
    if (Objects.nonNull(attributes)) {
      HttpServletRequest request = attributes.getRequest();
      RequestAround requestAround = new RequestAround();
      requestAround.setIp(IpUtil.getIpAddress(request));
      requestAround.setAUrl(request.getRequestURI());
      requestAround.setDevice(request.getHeader("platform"));
      requestAround.setVersion(request.getHeader("version"));
      requestAround.setHttpMethod(request.getMethod());
      requestAround.setClassMethod(getClassMethod(proceedingJoinPoint));
      requestAround.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
      requestAround.setResult(requestResult);
      requestAround.setTimeCost(System.currentTimeMillis() - start);
      logger.info("{}", JSON.toJSONString(requestAround));
    }
    return requestResult;
  }

  /**
   * 异常通知.
   *
   * @param joinPoint 切入点
   * @param e         异常信息
   * @author wangcaiwen
   * @since 2020/11/3 13:19
   */
  @AfterThrowing(pointcut = "requestPointCut()", throwing = "e")
  public void doAfterThrowing(JoinPoint joinPoint, RuntimeException e) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    if (Objects.nonNull(attributes)) {
      HttpServletRequest request = attributes.getRequest();
      RequestThrowing requestThrowing = new RequestThrowing();
      requestThrowing.setIp(IpUtil.getIpAddress(request));
      requestThrowing.setAUrl(request.getRequestURL().toString());
      requestThrowing.setDevice(request.getHeader("platform"));
      requestThrowing.setVersion(request.getHeader("version"));
      requestThrowing.setHttpMethod(request.getMethod());
      requestThrowing.setClassMethod(getClassMethod(joinPoint));
      requestThrowing.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
      requestThrowing.setExceptionInfo(e.getMessage());
      logger.error("{}", JSON.toJSONString(requestThrowing));
    }
  }

  /**
   * 获得类方法.
   *
   * @param joinPoint 接入点
   * @return 类方法
   * @author wangcaiwen
   * @since 2021/2/23 10:56
   */
  private String getClassMethod(JoinPoint joinPoint) {
    return String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName());
  }

  /**
   * 获得类方法.
   *
   * @param proceedingJoinPoint 切入点
   * @return 类方法
   * @author wangcaiwen
   * @since 2021/2/23 18:19
   */
  private String getClassMethod(ProceedingJoinPoint proceedingJoinPoint) {
    return String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
        proceedingJoinPoint.getSignature().getName());
  }

  /**
   * 获得参数.
   *
   * @param proceedingJoinPoint 切入点
   * @return 参数信息
   * @author wangcaiwen
   * @since 2021/2/23 10:57
   */
  private Map<String, Object> getRequestParamsByProceedingJoinPoint(
      ProceedingJoinPoint proceedingJoinPoint) {
    String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature())
        .getParameterNames();
    Object[] paramValues = proceedingJoinPoint.getArgs();
    return buildRequestParam(paramNames, paramValues);
  }

  /**
   * 获得参数.
   *
   * @param joinPoint 接入点
   * @return 参数信息
   * @author wangcaiwen
   * @since 2021/2/23 10:57
   */
  private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
    String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
    Object[] paramValues = joinPoint.getArgs();
    return buildRequestParam(paramNames, paramValues);
  }

  /**
   * 建立参数.
   *
   * @param paramNames  参数名
   * @param paramValues 参数值
   * @return 参数信息
   * @author wangcaiwen
   * @since 2020/11/3 13:04
   */
  private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
    Map<String, Object> requestParams = Maps.newHashMap();
    for (int i = 0; i < paramNames.length; i++) {
      if (!Objects.equals(paramNames[i], "request")
          && !Objects.equals(paramNames[i], "response")
          && !Objects.equals(paramNames[i], "bindingResult")) {
        Object value = paramValues[i];
        // 是否文件对象
        if (value instanceof MultipartFile) {
          MultipartFile file = (MultipartFile) value;
          // 获取文件名
          value = file.getOriginalFilename();
        }
        requestParams.put(paramNames[i], value);
      }
    }
    return requestParams;
  }

}
