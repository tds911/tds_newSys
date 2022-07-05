package com.tds.project.service;


import com.tds.project.domain.SysOperLog;

import java.util.List;

/**
 * 操作日志服务层
 */
public interface ISysOperLogService {
    /**
     * 操作日志
     */
    public void insertOperLog(SysOperLog operLog);

    /**
     * 查询系统操作日志集合
     * @param operLog
     * @return
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    /**
     * 删除系统操作日志
     * @param operIds
     * @return
     */
    public int deleteOperLogByids(Long[] operIds);

    /**
     * 查询操作日志详细
     * @param operId
     * @return
     */
    public SysOperLog selectOperLogById(Long operId);

    /**
     * 清空操作日志
     */
    public void  cleanOperLog();

    void recordPageLog(String pageName);
}
