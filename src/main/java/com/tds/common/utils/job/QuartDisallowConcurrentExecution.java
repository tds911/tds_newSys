package com.tds.common.utils.job;


import com.tds.project.domain.SysJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理
 */
@DisallowConcurrentExecution
public class QuartDisallowConcurrentExecution extends AbstractQuartzJob{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
