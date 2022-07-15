package com.tds.common.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "topom")
public class RouYiConfig {
    private String name;
    private String version;
    private String copyrightYear;
    private  boolean demoEnabled;
    private  static String profile;

    private static boolean addressEnabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public boolean isDemoEnabled() {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled) {
        this.demoEnabled = demoEnabled;
    }

    public static String getProfile() {
        return profile;
    }

    public static void setProfile(String profile) {
        RouYiConfig.profile = profile;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static void setAddressEnabled(boolean addressEnabled) {
        RouYiConfig.addressEnabled = addressEnabled;
    }
    public static String getAvatarPath(){
        return getProfile()+"/avatar";
    }
    public static String getDownloadPath(){
        return getProfile()+"/download/";
    }

    public static  String getUploadPath(){
        return getAvatarPath()+"/upload";
    }

}
