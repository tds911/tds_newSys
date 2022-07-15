package com.tds.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Base64 {

    public static String getImageBase64(InputStream inputStream){
        try{
            if (inputStream !=null){
                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                byte[] buffer=new byte[1024];
                int len=0;
                while ((len=inputStream.read(buffer))!=-1){
                    outputStream.write(buffer,0,len);
                }
                inputStream.close();
                if (outputStream !=null && outputStream.size() >0){
                    return org.apache.commons.codec.binary.Base64.encodeBase64String(outputStream.toByteArray());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
