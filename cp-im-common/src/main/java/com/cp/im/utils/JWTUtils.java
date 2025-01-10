package com.cp.im.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * @Description:
 * @Author xiajie
 * @Date 2021/3/23
 * @Version V2.0
 **/
public class JWTUtils {

    private static final String USERID = "userId";
    private static final String TOKEN = "token";
    public static final String KEY_TOKEN = "KEY_TOKEN:";

    private static final String TOKEN_SECRET = "raptor";

    /**
     * @Description: 加密
     * @Param: [userId]
     * @Return: java.lang.String
     * @Author: wangyingjie
     * @Date: 2020/7/1
     */
    public static String encode(String userId) {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        Map<String, Object> header = new HashMap<String, Object>(2) {
            {
                put("type", "JWT");
                put("alg", "HS256");
            }
        };

        return JWT.create().withHeader(header).withClaim("userId", userId).withClaim("sginature", DateUtils.getCurrentDateTime().toString()).sign(algorithm);
    }

    /**
     * @Description: 解密
     * @Param: [token]
     * @Return: com.auth0.jwt.interfaces.DecodedJWT
     * @Author: wangyingjie
     * @Date: 2020/7/1
     */
    public static DecodedJWT decode(String token) {
        return JWT.decode(token);
    }

    /**
     * 从token获取用户信息
     * @param request
     * @return userId
     */
    /*public static Long getUserIdByToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN);

        String s = decode(token).getClaim(USERID).asString();
        return Long.valueOf(s);
    }

    *//**
     * @Description: 从token获取用户信息userId 不可用于文件上传
     * @Author: xiajie
     * @return userId
     *//*
    public static Long getUserIdByToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return getUserIdByToken(request);
    }*/

    /**
     * 获取用户信息 不可用于文件上传
     * @return
     */
    public static Long getUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
//        if (request.getHeader(USERID)==null){
//            return 183083743L;
//        }
        return Long.valueOf(request.getHeader(USERID));
    }

}
