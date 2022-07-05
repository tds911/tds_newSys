package com.tds.common.security.handle;

import com.alibaba.fastjson.JSON;
import com.tds.common.constant.Constants;
import com.tds.common.constant.HttpStatus;
import com.tds.common.manager.factory.AsyncFactory;
import com.tds.common.manager.factory.AsyncManager;
import com.tds.common.security.service.TokenService;
import com.tds.common.utils.ServletUtils;
import com.tds.common.utils.StringUtils;
import com.tds.common.web.domain.server.AjaxResult;

import com.tds.project.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserEntity userEntity = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(userEntity)) {
            String userName = userEntity.getUsername();
            tokenService.delLogingUser(userEntity.getToken());
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
    }
}
