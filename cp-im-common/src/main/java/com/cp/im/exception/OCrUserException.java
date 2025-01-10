package com.cp.im.exception;

/**
 * @Description:
 * @Author wangxuewen
 * @Date 2021/12/17
 * @Version V3.0
 **/
public class OCrUserException extends RuntimeException {


    private String message;

    public OCrUserException(String message) {
        super(message);
        this.message = message;
    }
}
