package com.tds.project.service.impl;

import com.tds.common.exception.job.TaskException;
import com.tds.project.domain.SysJob;
import com.tds.project.service.DemoService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class DemoServiceImpl implements DemoService {
    private static final Logger log= LoggerFactory.getLogger(DemoServiceImpl.class);

    @Autowired
    private SysJobServiceImpl sysJobService;

    @Override
    public void demo1(SysJob job) throws SchedulerException, TaskException {
        System.out.println("================");
        List<SysJob> jobList = sysJobService.selectJobList(job);
        for (SysJob sysJob : jobList) {
            String jobName = sysJob.getJobName();
            if ("立案".equals(jobName)) {
                run(sysJob);
            }
            if ("不予立案".equals(jobName)){
                run(sysJob);
            }
        }

    }

    @Override
    public void demo() {
        SimpleDateFormat a = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date date = new Date();
        String b = a.format(date);
        System.out.println("时间:" + b + "====" + "测试");
    }

    @Override
    public void li() {
        SimpleDateFormat a = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date date = new Date();
        String b = a.format(date);
        System.out.println("时间"+b+"=========立案报告书===========");
    }

    @Override
    public void byli() {
        SimpleDateFormat a = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date date = new Date();
        String b = a.format(date);
        System.out.println("时间"+b+"=========不予立案报告书========");
    }

    public void run(SysJob sysJob) throws SchedulerException, TaskException {
        System.out.println(("==========开始修改)")+sysJob.getJobName()+("========="));

        SysJob job = new SysJob();

        Random r = new Random();
        int num = r.nextInt(10);
        String s = String.valueOf(num);

//        job.setCronExpression(("*/") + s + (" * * * * ?"));
        job.setCronExpression("0 "+"/1"+" "+" * * * ?");
        job.setJobId(sysJob.getJobId());

        job.setJobName(sysJob.getJobName());
        job.setStatus("0");
        job.setConcurrent(sysJob.getConcurrent());
        job.setInvokeTarget(sysJob.getInvokeTarget());
        job.setMisfirePolicy(sysJob.getMisfirePolicy());
        String jobGroup = sysJob.getJobGroup();
        sysJobService.updateSchedulerJob(job, jobGroup);
        log.error("随机CRON表达式:"+job.getCronExpression());
        System.out.println(("===========修改结束)")+sysJob.getJobName()+("==========="));
    }
}
