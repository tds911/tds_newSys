package com.tds.common.framework.aspectj.lang;

import com.alibaba.fastjson.JSON;
import com.tds.common.framework.aspectj.lang.annotation.Log;
import com.tds.common.framework.aspectj.lang.enums.BusinessStatus;
import com.tds.common.manager.factory.AsyncFactory;
import com.tds.common.manager.factory.AsyncManager;
import com.tds.common.security.service.TokenService;
import com.tds.common.utils.ServletUtils;
import com.tds.common.utils.Spring.SpringUtils;
import com.tds.common.utils.StringUtils;
import com.tds.common.utils.ip.IpUtils;

import com.tds.project.domain.SysOperLog;
import com.tds.project.domain.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.tds.common.framework.aspectj.lang.annotation.Log)")
    public void logPointCut() {
    }

    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    public void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            //获取注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            UserEntity userEntity = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
            SysOperLog sysOpenLog = new SysOperLog();
            sysOpenLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            sysOpenLog.setOperIp(ip);
            sysOpenLog.setJsonResult(JSON.toJSONString(jsonResult));
            sysOpenLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            if (userEntity != null) {
                sysOpenLog.setOperName(String.valueOf(userEntity.getUsername()));
            }
            if (e != null) {
                sysOpenLog.setStatus(BusinessStatus.FAIL.ordinal());
                sysOpenLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            String className = joinPoint.getTarget().getClass().getName();
            String method = joinPoint.getSignature().getName();
            sysOpenLog.setMethod(className + "." + method + "()");
            //设置请求方式
            sysOpenLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            //处理设置注解上的参数
            getControllerMethodDescription(joinPoint,controllerLog,sysOpenLog);
            //保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(sysOpenLog));


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog) throws Exception {
        operLog.setBusinessType(log.businessType().ordinal());
        operLog.setTitle(log.title());
        operLog.setOperatorType(log.operatorType().ordinal());
        if (log.isSaveRequestData()) {

        }
    }

    public Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    public void setRequestValue(JoinPoint joinPoint, SysOperLog operLog) throws Exception {
        String requestMethod = operLog.getRequestMethod();

    }
}
