package com.tds.common.utils.job;

import com.tds.common.utils.Spring.SpringUtils;
import com.tds.common.utils.StringUtils;

import com.tds.project.domain.SysJob;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class JobInvokeUtil {
    public static void invokeMethod(SysJob sysJob)throws Exception{
        String invokeTarget= sysJob.getInvokeTarget();
        String beanName=getBeanName(invokeTarget);
        String methodName=getMethodName(invokeTarget);
        List<Object[]> methodParams=getMethodParams(invokeTarget);
        if (!isValidClassName(beanName)){
            Object bean= SpringUtils.getBean(beanName);
            invokeMethod(bean,methodName,methodParams);
        }else {
            Object bean=Class.forName(beanName).newInstance();
            invokeMethod(bean,methodName,methodParams);
        }
    }


    private static void invokeMethod(Object bean,String methodName,List<Object[]>methodParams)
        throws NoSuchMethodException,SecurityException,IllegalAccessException,IllegalArgumentException, InvocationTargetException{
        if (StringUtils.isNotNull(methodParams)&&methodParams.size()>0){
            Method method=bean.getClass().getDeclaredMethod(methodName,getMethodParamsType(methodParams));
            method.invoke(bean,getMethodParamsValue(methodParams));
        }else {
            Method method=bean.getClass().getDeclaredMethod(methodName);
            method.invoke(bean);
        }
    }
    public static boolean isValidClassName(String invokeTarget){
        return org.apache.commons.lang3.StringUtils.countMatches(invokeTarget,".")>1;
    }
    public static String getBeanName(String invokeTarget){
        String beanName= org.apache.commons.lang3.StringUtils.substringBefore(invokeTarget,"(");
        return org.apache.commons.lang3.StringUtils.substringBeforeLast(beanName,".");
    }
    public static String getMethodName(String invokeTarget){
        String methodName=org.apache.commons.lang3.StringUtils.substringBefore(invokeTarget,"(");
                return org.apache.commons.lang3.StringUtils.substringAfter(methodName,".");
    }
    public static List<Object[]> getMethodParams(String invokeTarget){
        String methodStr= org.apache.commons.lang3.StringUtils.substringBetween(invokeTarget,"(",")");
        if (StringUtils.isEmpty(methodStr)){
            return null;
        }
        String[] methodParams=methodStr.split(",");
        List<Object[]> classs=new LinkedList<>();
        for (int i=0;i<methodParams.length;i++){
            String  str=org.apache.commons.lang3.StringUtils.trimToEmpty(methodParams[i]);
            if (org.apache.commons.lang3.StringUtils.contains(str,"'")){
                classs.add(new Object[]{org.apache.commons.lang3.StringUtils.replace(str,"",""),String.class});
            }
            else if (org.apache.commons.lang3.StringUtils.equals(str,"true")||org.apache.commons.lang3.StringUtils.equalsIgnoreCase(str,"false")){
                classs.add(new Object[]{Boolean.valueOf(str),Boolean.class});
            }
            else if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(str,"L")){
                classs.add(new Object[]{Long.valueOf(org.apache.commons.lang3.StringUtils.replaceIgnoreCase(str,"L","")),Long.class});
            }
            else if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(str,"D")){
                classs.add(new Object[]{Double.valueOf(org.apache.commons.lang3.StringUtils.replaceIgnoreCase(str,"D","")),Double.class});
            }else {
                classs.add(new Object[]{Integer.valueOf(str),Integer.class});
            }
        }
        return classs;
    }
    public static Class<?>[] getMethodParamsType(List<Object[]>methodParms){
        Class<?>[] classes=new Class<?>[methodParms.size()];
        int index=0;
        for (Object[] os:methodParms){
            classes[index]=(Class<?>) os[1];
            index++;
        }
        return classes;
    }
    public static Object[] getMethodParamsValue(List<Object[]>methodParams){
        Object[] classs=new Object[methodParams.size()];
        int index=0;
        for (Object[] os:methodParams){
            classs[index]=(Object)os[0];
            index++;
        }
        return classs;
    }
}
