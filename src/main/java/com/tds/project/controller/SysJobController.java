package com.tds.project.controller;

import com.github.pagehelper.PageHelper;
import com.tds.common.exception.job.TaskException;
import com.tds.common.utils.page.TableDateInfo;
import com.tds.common.web.controller.BaseController;
import com.tds.common.web.domain.server.AjaxResult;

import com.tds.project.domain.SysJob;
import com.tds.project.service.SysJobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController {
    @Autowired
    private SysJobService jobService;

    @GetMapping("/list")
    public TableDateInfo list( SysJob sysJob) {
        startPage();

        List<SysJob> list = jobService.selectJobList(sysJob);
        return getDataTable(list);
    }

    @GetMapping("/newlist")
    public List<SysJob> list(int pageNum,int pageSize) {
        List<SysJob> list = jobService.selectNewJobList(pageNum, pageSize);
        return list;
    }

    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysJob sysJob) throws SchedulerException, TaskException {
        return toAjax(jobService.insertJob(sysJob));
    }

    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SysJob sysJob) throws SchedulerException, TaskException {
        return toAjax(jobService.updateJob(sysJob));
    }

    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysJob sysJob) throws SchedulerException {
        SysJob newJob = jobService.selectJobById(sysJob.getJobId());
        newJob.setStatus(sysJob.getStatus());
        return toAjax(jobService.changeStatus(newJob));
    }

    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJob job) throws SchedulerException {
        jobService.run(job);
        return AjaxResult.success();
    }

    @DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds) throws SchedulerException, TaskException {
        jobService.deleteJobByIds(jobIds);
        return AjaxResult.success();
    }


    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId) {
        return AjaxResult.success(jobService.selectJobById(jobId));
    }
}
