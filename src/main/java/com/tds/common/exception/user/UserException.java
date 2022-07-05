package com.tds.common.exception.user;

import com.tds.common.exception.BaseException;

public class UserException extends BaseException {
    private static final long serialVersionUID=1;
    public UserException(String code,Object[] args){
        super("user",code,args,null);
    }
}
