package com.cp.im.exception;

/**
 * @Description:
 * @Author wangxuewen
 * @Date 2021/5/11
 * @Version V3.0
 **/
public class PlayOrderException extends RuntimeException {

    String message;

    public PlayOrderException(String message) {
        super(message);
    }

}
