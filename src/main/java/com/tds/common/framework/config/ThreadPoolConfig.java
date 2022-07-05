package com.tds.common.framework.config;

import com.tds.common.utils.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadPoolConfig {
    private int corePoolSize=50;

    private int maxPollSize=200;
    private  int queueCapacity=1000;;
    private  int keepAliveSeconds=300;
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPollSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean(name ="scheduledExecutorService")
    public ScheduledExecutorService scheduledExecutorService(){
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build())
        {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.pringException(r,t);
            }
        };
    }
}
