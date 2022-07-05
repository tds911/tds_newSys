package com.tds.common.utils.job;


import com.tds.project.domain.SysJob;
import org.quartz.JobExecutionContext;

public class QuartzJobExecution extends AbstractQuartzJob{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
