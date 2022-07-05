package com.tds.project.mapper;



import com.tds.project.domain.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOperLogMapper {
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

}
