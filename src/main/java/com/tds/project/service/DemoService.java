package com.tds.project.service;

import com.tds.common.exception.job.TaskException;

import com.tds.project.domain.SysJob;
import org.quartz.SchedulerException;

public interface DemoService {
    public void  demo1(SysJob job) throws SchedulerException, TaskException;

    public void demo();

    public void li();

    public void byli();
}
