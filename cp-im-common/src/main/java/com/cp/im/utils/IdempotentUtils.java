package com.cp.im.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.LRUMap;

/**
 * @Description 接口幂等性验证
 * @Author wangyingjie
 * @Date 2020/12/23
 * @Modified
 */
@Slf4j
public class IdempotentUtils {

    private static LRUMap<String, Integer> reqCache = new LRUMap<>(100);
    
    /** 
     * @Description: 防重复提交
     * @Param: [id, lockClass] 
     * @Return: boolean 
     * @Author: wangyingjie
     * @Date: 2020/12/23 
     */ 
    public static boolean judge(String id, Object lockClass) {
        synchronized (lockClass) {
            if (reqCache.containsKey(id)) {
                log.error("==> 重复提交[{}]", lockClass.getClass().getName());
                return false;
            }
            reqCache.put(id, 1);
        }

        return true;
    }
}
