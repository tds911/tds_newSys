package com.tds.common.core.text;

import com.tds.common.utils.StringUtils;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Convert {
    public static String utf8Str(Object obj) {
        return str(obj,CharsetKit.CHARSET_UTF_8);
    }

    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[] || obj instanceof Byte[]) {
            return str((Byte[]) obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer) obj, charset);
        }
        return obj.toString();
    }
    public static Integer toInt(Object value){
        return toInt(value,null);
    }
    public static String toStr(Object value,String defaultValue){
        if (null==value){
            return defaultValue;
        }
        if (value instanceof String){
            return (String) value;
        }
        return value.toString();
    }
    public static Integer toInt(Object value,Integer defaultValue){
        if (value==null){
            return defaultValue;
        }if (value instanceof Integer){
            return (Integer) value;
        }
        if (value instanceof  Number){
            return ((Number)value).intValue();
        }
        final String valueStr=toStr(value,null);
        if (StringUtils.isEmpty(valueStr)){
            return defaultValue;
        }
        try{
            return Integer.parseInt(valueStr.trim());
        }
        catch (Exception e){
            return defaultValue;
        }
    }
    public static Long toLong(Object value,Long defaultValue){
        if (value ==null){
            return defaultValue;
        }
        if (value instanceof Long){
            return (Long) value;
        }
        if (value instanceof Number){
            return ((Number) value).longValue();
        }
        final String valueStr=toStr(value,null);
        if (StringUtils.isEmpty(valueStr)){
            return defaultValue;
        }
        try {
            return new BigDecimal(valueStr.trim()).longValue();
        }catch (Exception e){
            return defaultValue;
        }
    }

    public static Double toDouble(Object value,Double defaultValue){
        if (value ==null){
            return defaultValue;
        }
        if (value instanceof Double){
            return (Double) value;
        }
        if (value instanceof Number){
            return ((Number) value).doubleValue();
        }
        final String valueStr=toStr(value,null);
        if (StringUtils.isEmpty(valueStr)){
            return defaultValue;
        }
        try {
            return new BigDecimal(valueStr.trim()).doubleValue();
        }catch (Exception e){
            return defaultValue;
        }
    }

    public static Float toFloat(Object value,Float defaultValue){
        if (value ==null){
            return defaultValue;
        }
        if (value instanceof Float){
            return (Float) value;
        }
        if (value instanceof Number){
            return ((Number) value).floatValue();
        }
        final String valueStr=toStr(value,null);
        if (StringUtils.isEmpty(valueStr)){
            return defaultValue;
        }
        try {
            return new BigDecimal(valueStr.trim()).floatValue();
        }catch (Exception e){
            return defaultValue;
        }
    }

    public static BigDecimal toBigDecimal(Object value,BigDecimal defaultValue){
        if (value ==null){
            return defaultValue;
        }
        if (value instanceof BigDecimal){
            return (BigDecimal) value;
        }
        if (value instanceof Long){
            return new BigDecimal((Long) value);
        }
        if (value instanceof Double){
            return new BigDecimal((Double) value);
        }
        if (value instanceof Integer){
            return new BigDecimal((Integer) value);
        }
        final String valueStr=toStr(value,null);
        if (StringUtils.isEmpty(valueStr)){
            return defaultValue;
        }
        try {
            return new BigDecimal(valueStr);
        }catch (Exception e){
            return defaultValue;
        }
    }

    public static String toStr(Object value){
        return toStr(value,null);
    }
    public static Long toLong(Object value){
        return toLong(value,null);
    }

    public static String[] toStrArray(String str){
        return toStrArray(",",str);
    }
    public static String[] toStrArray(String split,String str){
        return str.split(split);
    }
    public static Double toDouble(Object value){
        return toDouble(value,null);
    }
    public static Float toFloat(Object value){
        return toFloat(value,null);
    }
    public static BigDecimal toBigDecimal(Object value){
        return toBigDecimal(value,null);
    }
}
