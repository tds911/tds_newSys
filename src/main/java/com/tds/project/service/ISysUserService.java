package com.tds.project.service;


import com.tds.project.domain.SysUser;

public interface ISysUserService {

    public SysUser selectUserByUserName(String UserName);

    public int addUser(SysUser sysUser);

    public int selectByName(String userName);


}
