package com.tds.project.service;

import com.tds.common.exception.job.TaskException;

import com.tds.project.domain.SysJob;
import org.quartz.SchedulerException;

import java.util.List;

public interface SysJobService {
    public List<SysJob> selectJobList(SysJob job);

    public List<SysJob> selectNewJobList(int pageNum,int pageSize);

    public SysJob selectJobById(Long jobId);

    public int pauseJob(SysJob job)throws SchedulerException;
    public int deleteJob(SysJob job)throws SchedulerException;
    public void deleteJobByIds(Long[] jobIds) throws SchedulerException;
    public int resumeJob(SysJob job)throws SchedulerException;
    public int changeStatus(SysJob job)throws SchedulerException;
    public void run(SysJob job)throws SchedulerException;
    public int insertJob(SysJob job)throws SchedulerException, TaskException;
    public int updateJob(SysJob job)throws SchedulerException,TaskException;
    public boolean checkCronExpressionIsValid(String cronExpression);
}
