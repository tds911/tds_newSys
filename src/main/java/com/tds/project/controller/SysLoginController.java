package com.tds.project.controller;

import com.tds.common.constant.Constants;
import com.tds.common.security.service.SysLoginService;
import com.tds.common.security.service.SysPermissionService;
import com.tds.common.security.service.TokenService;

import com.tds.common.utils.SecurityUtils;
import com.tds.common.utils.ServletUtils;
import com.tds.common.utils.StringUtils;
import com.tds.common.web.controller.BaseController;
import com.tds.common.web.domain.server.AjaxResult;

import com.tds.project.domain.LoginBody;
import com.tds.project.domain.SysMenu;
import com.tds.project.domain.SysUser;
import com.tds.project.domain.UserEntity;
import com.tds.project.service.ISysMenuService;
import com.tds.project.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
public class SysLoginController extends BaseController {
    @Autowired
    private SysLoginService sysLoginService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysPermissionService permissionService;
    @Autowired
    private ISysMenuService menuService;


    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        System.out.println("-----"+loginBody.getUsername()+"----登录----");
        AjaxResult ajaxResult=AjaxResult.success();
        String token=sysLoginService.login(loginBody.getUsername(),loginBody.getPassword(),loginBody.getCode(),
                loginBody.getUuid());

        ajaxResult.put(Constants.TOKEN,token);
        ajaxResult.put("username",loginBody.getUsername());
        try{
            Map<String,Object>params=new HashMap<String,Object>();
            params.put("numbers",loginBody.getUsername());
            params.put("password",loginBody.getPassword());
//            String result= HttpUtil.get("localhost:8080/login/"+loginBody.getUsername(),5000);
//            ajaxResult.put("vimtoken",result);
//            System.out.println("查询成功:"+result.toString());

        }catch (Exception e){
            log.error("Token获取失败");
            e.printStackTrace();
        }
        return ajaxResult;
    }
    @PostMapping("/register")
    public AjaxResult register(@RequestBody SysUser sysUser){
        int i = userService.selectByName(sysUser.getUserName());
        if (i==0){
            if (StringUtils.isNotBlank(sysUser.getPassword())){
                sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
            }else {
                sysUser.setPassword(SecurityUtils.encryptPassword("123456"));
            }

        }
        return toAjax(userService.addUser(sysUser));
    }

    /**
     * 获取路由信息
     * @return
     */
    @GetMapping("/getRouters")
    public AjaxResult getRouters(){
        UserEntity userEntity=tokenService.getLoginUser(ServletUtils.getRequest());
        //用户信息
        SysUser uer=userEntity.getUser();
        List<SysMenu> menus=menuService.selectMenuTreeByUserId(uer.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 获取用户信息
     * @return
     */
    public AjaxResult getInfo(){
        UserEntity userEntity=tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser uer=userEntity.getUser();
        //角色集合
        Set<String> roles=permissionService.getRolePermission(uer);
        //权限集合
        Set<String> permissions=permissionService.getMenuPermission(uer);
        AjaxResult ajaxResult=AjaxResult.success();
        ajaxResult.put("user",uer);
        ajaxResult.put("roles",roles);
        ajaxResult.put("permission",permissions);
        return ajaxResult;
    }

}
