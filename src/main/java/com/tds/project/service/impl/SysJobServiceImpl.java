package com.tds.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.tds.common.constant.ScheduleConstants;
import com.tds.common.exception.job.TaskException;
import com.tds.common.utils.job.CronUtils;
import com.tds.common.utils.job.ScheduleUtils;

import com.tds.project.domain.SysJob;
import com.tds.project.mapper.SysJobMapper;
import com.tds.project.service.SysJobService;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class SysJobServiceImpl implements SysJobService {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SysJobMapper sysJobMapper;


    @PostConstruct
    public void init() throws SchedulerException, TaskException {
        scheduler.clear();
        List<SysJob> jobList = sysJobMapper.selectJobAll();
        for (SysJob job : jobList) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }

    @Override
    @Transactional
    public int insertJob(SysJob job) throws SchedulerException, TaskException {
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = sysJobMapper.insertJob(job);
        if (rows > 0) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }


    @Override
    public List<SysJob> selectJobList(SysJob job) {
        return sysJobMapper.selectJobList(job);
    }

    @Override
    public List<SysJob> selectNewJobList(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return sysJobMapper.selectNewJobList();
    }

    @Override
    public SysJob selectJobById(Long jobId) {
        return sysJobMapper.selectJobById(jobId);
    }

    /**
     * 暂停
     * @param job
     * @return
     * @throws SchedulerException
     */
    @Override
    @Transactional
    public int pauseJob(SysJob job) throws SchedulerException {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = sysJobMapper.updateJob(job);
        if (rows > 0) {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    /**
     * 删除任务，所对应的trigger也将被删除
     * @param job
     * @throws SchedulerException
     */
    @Override
    @Transactional
    public int deleteJob(SysJob job) throws SchedulerException {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        int rows = sysJobMapper.deleteJob(jobId);
        if (rows > 0) {
            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    /**
     * 删除调度信息
     * @param jobIds
     * @throws SchedulerException
     */
    @Override
    @Transactional
    public void deleteJobByIds(Long[] jobIds) throws SchedulerException {
        for (Long jobId : jobIds) {
            SysJob job = sysJobMapper.selectJobById(jobId);
            deleteJob(job);
        }
    }

    /**
     * 恢复任务
     * @param job
     * @return
     * @throws SchedulerException
     */
    @Override
    public int resumeJob(SysJob job) throws SchedulerException {
        Long jobId=job.getJobId();
        String jobGruop=job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows=sysJobMapper.updateJob(job);
        if (rows>0){
            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId,jobGruop));
        }
        return sysJobMapper.updateJob(job);
    }

    /**
     * 任务调度修改
     * @param job
     * @return
     * @throws SchedulerException
     */
    @Override
    @Transactional
    public int changeStatus(SysJob job) throws SchedulerException {
        int rows = 0;
        String status=job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)){
            rows=resumeJob(job);
        }
        else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)){
            rows=pauseJob(job);
        }
        return rows;
    }

    /**
     * 立即运行
     * @param job
     * @throws SchedulerException
     */
    @Override
    @Transactional
    public void run(SysJob job) throws SchedulerException {
        Long jobId= job.getJobId();
        String jobGroup=job.getJobGroup();
        SysJob properties=selectJobById(job.getJobId());
        //参数
        JobDataMap dataMap=new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES,properties);
        scheduler.triggerJob(ScheduleUtils.getJobKey(jobId,jobGroup),dataMap);
    }

    /**
     * 更新任务的时间表达式
     * @param job
     * @return
     * @throws SchedulerException
     * @throws TaskException
     */
    @Override
    @Transactional
    public int updateJob(SysJob job) throws SchedulerException, TaskException {
        SysJob properties=selectJobById(job.getJobId());
        int rows=sysJobMapper.updateJob(job);
        if (rows>0){
            updateSchedulerJob(job,properties.getJobGroup());
        }
        return rows;
    }

    /**
     * 检验表达式是否有效
     * @param cronExpression
     * @return
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }

    /**
     * 更新任务
     * @param job
     * @param jobGroup
     * @throws SchedulerException
     * @throws TaskException
     */
    public void updateSchedulerJob(SysJob job,String jobGroup)throws SchedulerException,TaskException{
        Long jobId= job.getJobId();
        //判断是否存在
        JobKey jobKey=ScheduleUtils.getJobKey(jobId,jobGroup);
        if (scheduler.checkExists(jobKey)){
            //防止创建是出现数据问题，先移除，人后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler,job);
    }
}
