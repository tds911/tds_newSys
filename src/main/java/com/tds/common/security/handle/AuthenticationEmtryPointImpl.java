package com.tds.common.security.handle;

import com.alibaba.fastjson.JSON;
import com.tds.common.constant.HttpStatus;
import com.tds.common.utils.ServletUtils;
import com.tds.common.utils.StringUtils;
import com.tds.common.web.domain.server.AjaxResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class AuthenticationEmtryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID= -8970718410437077606L;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
        int code= HttpStatus.UNAUTHORIZED;
        String msg= StringUtils.format("请求访问:{},认证失败，无法访问系统资源",request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(code,msg)));
    }
}
