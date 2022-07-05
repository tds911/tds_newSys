package com.tds.common.enums;

public enum UserStatus {
    OK("0","正常"),
    DISABLE("1","停用"),
    DELETED("2","删除"),
    SP("9","审批中"),
    SP_ERROR("8","审批不通过");
    private final String code;
    private  final String info;
    UserStatus(String code,String info){
        this.code=code;
        this.info=info;
    }
    public String getCode(){
        return code;
    }
    public String getInfo(){
        return info;
    }
}
