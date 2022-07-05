package com.tds.project.mapper;


import com.tds.project.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISysUserMapper {
    public SysUser selectUserByUserName(String UserName);

    public int addUser(SysUser sysUser);

    public int selectByName(String userName);
}
