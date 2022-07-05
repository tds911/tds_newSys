package com.tds.common.exception;

import com.tds.common.utils.MessageUtils;
import com.tds.common.utils.StringUtils;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private String module;
    private String code;
    private Object[] args;
    private String defaultMessage;

    public BaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }
    public Object[] getArgs(){
        return args;
    }
    public String getDefaultMessage(){
        return defaultMessage;
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isNotEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }
}
