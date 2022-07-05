package com.tds.project.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 验证码实体类
 */


public class UserEntity implements UserDetails {
    private static final long serialVersionUID=1L;
    /**
     * token 登陆凭证
     */
    private String token;
    /**
     * token 过期时间
     */
    private long expireTime;
    /**
     *  登录时间
     */
    private long loginTime;
    private com.tds.project.domain.SysUser user;
    private Set<String> permissions;

    public UserEntity() {
    }

    public UserEntity(com.tds.project.domain.SysUser user, Set<String> permissions) {
        this.user=user;
        this.permissions=permissions;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public com.tds.project.domain.SysUser getUser() {
        return user;
    }

    public void setUser(com.tds.project.domain.SysUser user) {
        this.user = user;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }




}
