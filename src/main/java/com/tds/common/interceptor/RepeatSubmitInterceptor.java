package com.tds.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tds.common.interceptor.annotation.RepeatSubmit;
import com.tds.common.utils.ServletUtils;
import com.tds.common.web.domain.server.AjaxResult;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 防止重复提交拦截器
 */
@Component
public abstract class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       if (handler instanceof HandlerMethod){
           HandlerMethod handlerMethod=(HandlerMethod) handler;
           Method method=handlerMethod.getMethod();
           RepeatSubmit annotation=method.getAnnotation(RepeatSubmit.class);
           if (annotation!=null){
               if (this.isRepeatSubmit(request)){
                   AjaxResult ajaxResult=AjaxResult.error("不允许重复提交，请稍后再试");
                   ServletUtils.renderString(response, JSONObject.toJSONString(ajaxResult));
                   return false;
               }
           }
           return true;
       }
       else {
           return super.preHandle(request,response,handler);
       }
    }

    public abstract boolean isRepeatSubmit(HttpServletRequest request);
}
