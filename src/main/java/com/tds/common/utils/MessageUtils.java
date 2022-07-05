package com.tds.common.utils;

import com.tds.common.utils.Spring.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageUtils {
    public static String message(String code,Object... args){
        MessageSource messageSource= SpringUtils.getBean(MessageSource.class);
        return messageSource.getMessage(code,args, LocaleContextHolder.getLocale());
    }
}
