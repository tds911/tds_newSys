package com.tds.common.utils.job;

import com.tds.common.constant.ScheduleConstants;
import com.tds.common.exception.job.TaskException;

import com.tds.project.domain.SysJob;
import org.quartz.*;

public class ScheduleUtils {
    private static Class<? extends Job>getQuartzJobClass(SysJob sysJob){
        boolean isConcurrent ="0".equals(sysJob.getConcurrent());
        return isConcurrent ? QuartzJobExecution.class : QuartDisallowConcurrentExecution.class;

    }
    public static TriggerKey getTriggerKey(Long jobId,String jobGroup){
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME+jobId,jobGroup);
    }
    public static JobKey getJobKey(Long jobId,String jobGroup){
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME+jobId,jobGroup);
    }
    public static void createScheduleJob(Scheduler scheduler,SysJob job)throws SchedulerException, TaskException{
        Class<? extends Job>jobClass=getQuartzJobClass(job);
        Long jobId=job.getJobId();
        String jobGroup=job.getJobGroup();
        JobDetail jobDetail=JobBuilder.newJob(jobClass).withIdentity(getJobKey(jobId,jobGroup)).build();
        CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule(job.getCronExpression());
        cronScheduleBuilder=handleCronScheduleMisfirePolicy(job,cronScheduleBuilder);
        CronTrigger trigger=TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobId,jobGroup)).withSchedule(cronScheduleBuilder).build();

        jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES,job);
        if (scheduler.checkExists(getJobKey(jobId,jobGroup))){
            scheduler.deleteJob(getJobKey(jobId,jobGroup));
        }
        //暂停任务
        scheduler.scheduleJob(jobDetail,trigger);
        if (job.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())){
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId,jobGroup));
        }
    }
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob job,CronScheduleBuilder cb)throws TaskException{
        switch (job.getMisfirePolicy()){
            case ScheduleConstants.MISFIRE_DEFAULT:
                return cb;
            case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
                return cb.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
                return cb.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleConstants.MISFIRE_DO_NOTHING:
                return cb.withMisfireHandlingInstructionDoNothing();
            default:
                throw new TaskException("The task misfire policy'"+job.getMisfirePolicy()+"'cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
        }
    }
}
