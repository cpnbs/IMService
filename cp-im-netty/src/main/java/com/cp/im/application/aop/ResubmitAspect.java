package com.cp.im.application.aop;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cp.im.error.ErrorCode;
import com.cp.im.infrastructure.annotation.Resubmit;
import com.cp.im.infrastructure.utils.RedisUtils;
import com.cp.im.result.Result;
import com.cp.im.utils.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 切面处理.
 */

@Aspect
@Component
public class ResubmitAspect {

  @Resource
  private RedisUtils redisUtils;

  /**
   * 重复提交验证.
   *
   * @param resubmit 对应注解
   * @author wangcaiwen
   * @since 2021/4/16 14:21
   */
  @Pointcut("@annotation(resubmit)")
  public void pointCut(Resubmit resubmit) {
  }

  /**
   * 重复提交验证.
   *
   * @param proceedingJoinPoint 切入点
   * @param resubmit 注解信息
   * @return 参数信息
   * @author wangcaiwen
   * @since 2021/4/16 14:23
   */
  @Around(value = "pointCut(resubmit)", argNames = "proceedingJoinPoint,resubmit")
  public Object doAround(ProceedingJoinPoint proceedingJoinPoint, Resubmit resubmit) throws Throwable {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (Objects.nonNull(attributes)) {
      HttpServletRequest request = attributes.getRequest();
      int time = resubmit.time();
      String userId = request.getHeader("userId");
      if (StringUtils.isEmpty(userId)) {
        userId = getParametersUserId(proceedingJoinPoint);
      }
      if (Objects.isNull(userId) || StringUtils.isEmpty(userId)) {
        return Result.error(ErrorCode.ERROR_SUBMIT);
      }
      String path = request.getServletPath();
      String flagKey = "KEY_CHAT_AUT_NO_RESUBMIT:" + userId + "_" + path;
      if (redisUtils.hasKey(flagKey)) {
        return Result.error(ErrorCode.DUPLICATE_REQUEST);
      }
      String clientId = IdUtil.simpleUUID();
      this.redisUtils.set(flagKey, clientId, time);
    }
    return proceedingJoinPoint.proceed();
  }

  /**
   * 获得用户ID.
   *
   * @param proceedingJoinPoint 切入点
   * @return j用户ID
   * @author wangcaiwen
   * @since 2021/4/10 16:19
   */
  private String getParametersUserId(ProceedingJoinPoint proceedingJoinPoint) {
    // 参数名
    String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
    // 参数值
    Object[] paramValues = proceedingJoinPoint.getArgs();
    String userId = null;
    for (int i = 0; i < paramNames.length; i++) {
      if (!Objects.equals(paramNames[i], "request")
          && !Objects.equals(paramNames[i], "response")
          && !Objects.equals(paramNames[i], "bindingResult")) {
        Object value = paramValues[i];
        String s = JSONObject.toJSONString(value);
        Map<String, Object> object = JSONObject.parseObject(s);
        String userKey = "userId";
        String sponsorKey = "sponsorId";
        if (object.containsKey(userKey)) {
          userId = MapUtils.getString(object, userKey);
          break;
        }
        if (object.containsKey(sponsorKey)) {
          userId = MapUtils.getString(object, sponsorKey);
          break;
        }
      }
    }
    return userId;
  }

}
