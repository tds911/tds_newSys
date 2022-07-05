package com.tds.project.service.impl;

import com.tds.common.framework.aspectj.lang.enums.BusinessStatus;
import com.tds.common.security.service.TokenService;
import com.tds.common.utils.DateUtils;
import com.tds.common.utils.ServletUtils;
import com.tds.common.utils.Spring.SpringUtils;
import com.tds.common.utils.ip.IpUtils;

import com.tds.project.domain.SysOperLog;
import com.tds.project.domain.SysUser;
import com.tds.project.mapper.SysOperLogMapper;
import com.tds.project.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ISysOperLogServiceImpl implements ISysOperLogService {
    @Autowired
    private SysOperLogMapper operLogMapper;

    @Override
    public void insertOperLog(SysOperLog operLog) {
        operLogMapper.insertOperLog(operLog);
    }

    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return operLogMapper.selectOperLogList(operLog);
    }

    @Override
    public int deleteOperLogByids(Long[] operIds) {
        return operLogMapper.deleteOperLogByids(operIds);
    }

    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return operLogMapper.selectOperLogById(operId);
    }

    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }

    @Override
    public void recordPageLog(String pageName) {
        SysOperLog sysOperLog=new SysOperLog();
        //获取当前用户
        SysUser sysUser= SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest()).getUser();
        sysOperLog.setTitle("页面日志记录");
        sysOperLog.setBusinessType(0);
        sysOperLog.setOperatorType(1);

        sysOperLog.setOperName(sysUser.getUserName());

        sysOperLog.setStatus(BusinessStatus.SUCCESS.ordinal());
        //请求的地址
        String ip= IpUtils.getIpAddr(ServletUtils.getRequest());
        sysOperLog.setOperIp(ip);
        sysOperLog.setOperTime(DateUtils.getNowDate());
        sysOperLog.setMethodTitle(pageName);

        operLogMapper.insertOperLog(sysOperLog);


    }
}
