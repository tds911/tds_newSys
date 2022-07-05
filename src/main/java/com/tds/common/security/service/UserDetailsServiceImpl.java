package com.tds.common.security.service;

import com.tds.common.enums.UserStatus;
import com.tds.common.exception.BaseException;

import com.tds.common.utils.StringUtils;

import com.tds.project.domain.SysUser;
import com.tds.project.domain.UserEntity;
import com.tds.project.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private ISysUserService userService;
    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户:{}不存在.", username);
            throw new UsernameNotFoundException("登录用户:" + username + "不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户:{} 已被删除.", username);
            throw new BaseException("对不起，您的账号:" + username + "已被删除");
        }else if (UserStatus.DISABLE.getCode().equals(user.getStatus())){
            log.info("登录用户:{} 已被停用.",username);
            throw new BaseException("对不起，您的账号:"+username+"已停用");
        }
        else if (UserStatus.SP.getCode().equals(user.getStatus())){
            log.info("账号审批中",username);
            throw new BaseException("账号审批中");
        }
        else if (UserStatus.SP_ERROR.getCode().equals(user.getStatus())){
            log.info("账号审批不通过",username);
            throw new BaseException(("账号审批不通过，原因:"+user.getRemark()));
        }
        return createLoginUser(user);
    }
    public UserDetails createLoginUser(SysUser user){
        return new UserEntity(user,permissionService.getMenuPermission(user));
    }
}
