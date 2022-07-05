package com.tds.project.service.impl;


import com.tds.project.domain.SysJobLog;
import com.tds.project.mapper.ISysJobLogMapper;
import com.tds.project.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISysJobLogServiceImpl implements ISysJobLogService {
    @Autowired
    private ISysJobLogMapper iSysJobLogMapper;

    @Override
    public void addJobLog(SysJobLog jobLog) {
        iSysJobLogMapper.insertJobLog(jobLog);
    }

    @Override
    public List<SysJobLog> selectJobLogList(SysJobLog sysJobLog) {
        return iSysJobLogMapper.selectJobList(sysJobLog);
    }

    @Override
    public SysJobLog selectJobLogById(Long jobLogId) {
        return iSysJobLogMapper.selectJobLogById(jobLogId);
    }

    @Override
    public int deleteJobLogByIds(Long[] logIds) {
        return iSysJobLogMapper.deleteJobLogByIds(logIds);
    }

    @Override
    public int deleteJobLogById(Long jonId) {
        return iSysJobLogMapper.deleteJobLogById(jonId);
    }

    @Override
    public void cleanJobLog() {
        iSysJobLogMapper.cleanJobLog();
    }
}
