package com.cp.im.pojo;

import lombok.Data;

/**
 * @Description 基础信息
 * @Author wangyingjie
 * @Date 15:13 2020/5/11
 * @Modified
 */
@Data
public class Base {

    /** 用户ID */
    public Long userId;

    public Base() {
    }

    public Base(Long userId) {
        this.userId = userId;
    }
}
