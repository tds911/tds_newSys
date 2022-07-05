package com.tds.project.service;


import com.tds.project.domain.SysJobLog;

import java.util.List;

public interface ISysJobLogService {
    /**
     * 新增任务日志
     * @param jobLog
     */
    public void addJobLog(SysJobLog jobLog);

    public List<SysJobLog>selectJobLogList(SysJobLog sysJobLog);

    public SysJobLog selectJobLogById(Long jobLogId);


    public int deleteJobLogByIds(Long[] logIds);

    public int deleteJobLogById(Long jonId);

    public void cleanJobLog();
}
