package com.tds.project.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class SysJobLog extends BaseEntity{
    private static final long serialVersionUID=1L;
    private String jobName;
    private Long jobLogId;
    private String jobGroup;
    private String jobMessage;
    private String status;
    private String invokeTarget;
    private String exceptionInfo;
    private Date startTime;
    private Date stopTime;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getJobLogId() {
        return jobLogId;
    }

    public void setJobLogId(Long jobLogId) {
        this.jobLogId = jobLogId;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobMessage() {
        return jobMessage;
    }

    public void setJobMessage(String jobMessage) {
        this.jobMessage = jobMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getInvokeTarget() {
        return invokeTarget;
    }

    public void setInvokeTarget(String invokeTarget) {
        this.invokeTarget = invokeTarget;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("jobLogId",getJobLogId())
                .append("jobName",getJobName())
                .append("jobGroup",getJobGroup())
                .append("jobMessage",getJobMessage())
                .append("status",getStatus())
                .append("exceptionInfo",getExceptionInfo())
                .append("startTime",getStartTime())
                .append("stopTime",getStopTime())
                .toString();

    }
}
