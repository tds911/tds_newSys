package com.tds.common.manager.factory;

import com.tds.common.constant.Constants;

import com.tds.common.utils.LogUtils;
import com.tds.common.utils.ServletUtils;
import com.tds.common.utils.Spring.SpringUtils;
import com.tds.common.utils.ip.AddressUtils;
import com.tds.common.utils.ip.IpUtils;

import com.tds.project.domain.SysLogininfor;
import com.tds.project.domain.SysOperLog;
import com.tds.project.service.ISysLogininforService;
import com.tds.project.service.ISysOperLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 */

public class AsyncFactory {
    private static  final Logger sys_user_logger= LoggerFactory.getLogger("sys-user");


    public static TimerTask recordLogininfor(final String username,final String status,final String message,final Object... args){
        final UserAgent userAgent=UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip= IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address= AddressUtils.getRealAddressByIP(ip);
                StringBuilder s=new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                //打印信息到日志
                sys_user_logger.info(s.toString(),args);
                //获取客户端操作系统
                String os=userAgent.getOperatingSystem().getName();
                //获取客户到浏览器
                String browser=userAgent.getBrowser().getName();
                //封装对象
                SysLogininfor sysLoininfor=new SysLogininfor();
                sysLoininfor.setUserName(username);
                sysLoininfor.setIpaddr(ip);
                sysLoininfor.setLoginLocation(address);
                sysLoininfor.setBrowser(browser);
                sysLoininfor.setOs(os);
                sysLoininfor.setMsg(message);
                //日志状态
                if (Constants.LOGIN_SUCCESS.equals(status)||Constants.LOGOUT.equals(status)){
                    sysLoininfor.setStatus(Constants.SUCCESS);
                }else if (Constants.LOGIN_FAIL.equals(status)){
                    sysLoininfor.setStatus(Constants.FAIL);
                }
                //插入数据
                SpringUtils.getBean(ISysLogininforService.class).insertLogininfor(sysLoininfor);
            }
        };
    }

    public static TimerTask recordOper(final SysOperLog operLog){
        return new TimerTask() {
            @Override
            public void run() {
                //远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(ISysOperLogService.class).insertOperLog(operLog);
            }
        };
    }

}
