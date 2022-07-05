package com.tds.project.domain;

public class SysUser extends BaseEntity{
    private static final long serialVersionUID=1L;

    private Long userId;
    private String userName;
    private String nickName;
    private String password;
    private String status;
    private String phoneNumber;
    private String loginTime;
    private String expireTime;
    private String delFlag;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public boolean isAdmin(){
        return isAdmin(this.userId);
    }
    public static boolean isAdmin(Long userId){
        return userId != null && 1L==userId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", expireTime='" + expireTime + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
