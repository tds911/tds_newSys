package com.tds.project.mapper;


import com.tds.project.domain.SysJobLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISysJobLogMapper {

    public int insertJobLog(SysJobLog sysJobLog);

    public List<SysJobLog> selectJobList(SysJobLog jobLog);

    public List<SysJobLog> selectJobLogAll();
    public SysJobLog selectJobLogById(Long jobLogId);
    public int deleteJobLogByIds(Long[] jobIds);
    public int deleteJobLogById(Long jobId);
    public void cleanJobLog();
}
