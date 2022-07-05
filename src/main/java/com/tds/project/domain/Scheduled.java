package com.tds.project.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * scheduled
 */
@Accessors(chain = true)
@Data
public class Scheduled {
    /**
     * scheduled.id
     */
    private Integer id;
 
    /**
     * scheduled.task_key
     * 任务key值（使用bean名称）
     */
    private String taskKey;
 
    /**
     * scheduled.name
     * 任务名称
     */
    private String name;
 
    /**
     * scheduled.cron
     * 任务表达式
     */
    private String cron;
 
    /**
     * scheduled.status
     * 状态(0.禁用; 1.启用)
     */
    private Integer status;
 
    /**
     * scheduled.create_time
     * 创建时间
     */
    private LocalDateTime createTime;
 
    /**
     * scheduled.update_time
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Scheduled{" +
                "id=" + id +
                ", taskKey='" + taskKey + '\'' +
                ", name='" + name + '\'' +
                ", cron='" + cron + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}