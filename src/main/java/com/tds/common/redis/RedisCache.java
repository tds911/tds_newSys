package com.tds.common.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = {"uncheched","rawtypes"})
@Component
public class RedisCache {
    @Autowired
    public RedisTemplate redisTemplate;

    public <T> ValueOperations<String,T>setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit){
        ValueOperations<String,T>operation=redisTemplate.opsForValue();
        operation.set(key, value,timeout,timeUnit);
        return operation;
    }

    public <T>  ValueOperations<String,T> setCacheObject(String key,T value){
        ValueOperations<String,T> operation=redisTemplate.opsForValue();
        operation.set(key,value);
        return operation;
    }
    public <T> T getCacheObject(String key){
        ValueOperations<String,T>operation=redisTemplate.opsForValue();
        return operation.get(key);
    }
    public void deleteObject(String key){
        redisTemplate.delete(key);
    }
}
