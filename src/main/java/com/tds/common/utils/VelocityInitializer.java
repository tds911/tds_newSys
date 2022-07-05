package com.tds.common.utils;

import com.tds.common.constant.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

public class VelocityInitializer {

    public static void initVelocity(){
        Properties p=new Properties();
        try {
            p.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

            p.setProperty(Velocity.ENCODING_DEFAULT, Constants.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING,Constants.UTF8);
            Velocity.init(p);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
