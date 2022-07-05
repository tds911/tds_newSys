package com.tds.common.utils.ip;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressUtils {
    private static final Logger log= LoggerFactory.getLogger(AddressUtils.class);
    public static  final String UNKNOWN="XX XX";
    public static String getRealAddressByIP(String ip){
        String address= UNKNOWN;
        if (IpUtils.internalIp(ip)){
            return "内网IP";
        }
        return address;
    }

}
