package com.tds.common.utils.job;

import com.tds.common.constant.Constants;
import com.tds.common.constant.ScheduleConstants;
import com.tds.common.utils.ExceptionUtil;
import com.tds.common.utils.Spring.SpringUtils;
import com.tds.common.utils.StringUtils;
import com.tds.common.utils.bean.BeanUtils;

import com.tds.project.domain.SysJob;
import com.tds.project.domain.SysJobLog;
import com.tds.project.service.ISysJobLogService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public abstract class AbstractQuartzJob implements Job {
    private static final Logger log= LoggerFactory.getLogger(AbstractQuartzJob.class);
    /**线程本地变量*/
    private static ThreadLocal<Date> threadLocal=new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SysJob sysJob=new SysJob();
        BeanUtils.copyBeanProp(sysJob,jobExecutionContext.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try{
            before(jobExecutionContext,sysJob);
            if (sysJob!=null){
                doExecute(jobExecutionContext,sysJob);
            }
            after(jobExecutionContext,sysJob,null);
        }
        catch (Exception e){
            log.error("任务执行异常 - :",e);
            after(jobExecutionContext,sysJob,e);
        }


    }
    protected void before(JobExecutionContext context,SysJob sysJob){
        threadLocal.set(new Date());
    }
    protected abstract void doExecute(JobExecutionContext context,SysJob sysJob)throws Exception;
    protected void after(JobExecutionContext context,SysJob sysJob,Exception e){
        Date startTime=threadLocal.get();
        threadLocal.remove();
        final SysJobLog sysJobLog=new SysJobLog();
        sysJobLog.setJobName(sysJob.getJobName());
        sysJobLog.setJobGroup(sysJob.getJobGroup());
        sysJobLog.setInvokeTarget(sysJob.getInvokeTarget());
        sysJobLog.setStartTime(startTime);
        sysJobLog.setStopTime(new Date());
        long runMs=sysJobLog.getStopTime().getTime()-sysJobLog.getStartTime().getTime();
        sysJobLog.setJobMessage(sysJobLog.getJobName()+"总共耗时："+runMs+"毫秒");
        if (e!=null){
            sysJobLog.setStatus(Constants.FAIL);
            String errorMsg= StringUtils.substring(ExceptionUtil.getExceptionMessage(e),0,2000);
            sysJobLog.setExceptionInfo(errorMsg);
        }
        else {
            sysJobLog.setStatus(Constants.SUCCESS);
        }
        SpringUtils.getBean(ISysJobLogService.class).addJobLog(sysJobLog);
    }

}
