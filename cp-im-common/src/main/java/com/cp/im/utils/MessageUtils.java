package com.cp.im.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

    public  static String getLocale(String msgCode, Object... args) {

        try {
            MessageSource messageSource = SpringUtils.getBean(MessageSource.class);
            return messageSource.getMessage(msgCode, args, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            System.out.println("异常了："+e.getMessage());
            return msgCode;
        }
    }


}
