package com.tds.common.exception.user;

public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
