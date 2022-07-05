package com.tds.project.service;


import com.tds.project.domain.Scheduled;

/**
 * @ClassName ScheduledTaskService
 * @Description 定时任务接口
 * @Author lv617
 * @Date 2020/9/8 10:51
 * @Version 1.0
 */
public interface ScheduledTaskService {
 
    /**
     * 根据任务key 启动任务
     */
    Boolean start(String taskKey, Scheduled scheduled);
 
    /**
     * 根据任务key 停止任务
     */
    Boolean stop(String taskKey);
 
    /**
     * 根据任务key 重启任务
     */
    Boolean restart(String taskKey, Scheduled scheduled);
 
    /**
     * 初始化  ==> 启动所有正常状态的任务
     */
    void initAllTask();

    int updateSchedule(Scheduled scheduled);
}