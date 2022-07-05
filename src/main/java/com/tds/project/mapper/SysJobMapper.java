package com.tds.project.mapper;



import com.tds.project.domain.SysJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysJobMapper {
    public List<SysJob> selectJobAll();

    public List<SysJob> selectJobList(SysJob job);

    public List<SysJob> selectNewJobList();

    public SysJob selectJobById(Long jobId);

    public int deleteJobByIds(Long[] ids);

    public int deleteJob(Long jobId);

    public int insertJob(SysJob job);

    public int updateJob(SysJob job);
}
