package com.tds.project.controller;


import com.tds.common.utils.SecurityUtils;
import com.tds.common.utils.StringUtils;
import com.tds.common.web.controller.BaseController;
import com.tds.common.web.domain.server.AjaxResult;

import com.tds.project.domain.SysUser;
import com.tds.project.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody SysUser sysUser) {
        int i = userService.selectByName(sysUser.getUserName());
        if (i == 0) {
            if (StringUtils.isNotBlank(sysUser.getPassword())) {
                sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
            } else {
                sysUser.setPassword(SecurityUtils.encryptPassword("123456"));
            }

        }else {
            return AjaxResult.error("用户:"+sysUser.getUserName()+"已存在,请换一个用户名");
        }
        return toAjax(userService.addUser(sysUser));
    }


}
