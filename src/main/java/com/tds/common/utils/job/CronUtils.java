package com.tds.common.utils.job;



import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * CRON表示式工具类
 */
public class CronUtils {
    public static boolean isValid(String cronExpression){
        return CronExpression.isValidExpression(cronExpression);
    }
    public static String getInvalidMessage(String cronExpression){
        try{
            new CronExpression(cronExpression);
            return null;
        }
        catch (ParseException pe){
            return pe.getMessage();
        }
    }

    public static Date getNextExecution(String cronExpression){
        try
        {
            CronExpression cron=new CronExpression(cronExpression);
            return cron.getNextInvalidTimeAfter(new Date(System.currentTimeMillis()));
        }
        catch (ParseException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
