package com.tds.common.core.text;

import com.tds.common.utils.StringUtils;

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

    public static String[] toStrArray(String str){
        return toStrArray(",",str);
    }
    public static String[] toStrArray(String split,String str){
        return str.split(split);
    }
}
