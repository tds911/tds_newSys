package com.tds.common.core;

import cn.hutool.core.exceptions.UtilException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import static cn.hutool.core.util.RandomUtil.getRandom;

public final class UUID implements java.io.Serializable,Comparable<UUID>{
    private static final long serialVersionUID=-1185015143654744140L;


    private static class Holder{
        static final SecureRandom numberGenerator=getSecureRamdom();
    }
    private long mostSigBits;
    private long leastSigBits;
    private UUID(byte[] data){
        long msb=0;
        long lsb=0;
        assert data.length==16:"data must be 16 bytes in length";
        for (int i=0;i<8;i++){
            msb=(msb <<8)|(data[i]&0xff);
        }
        for (int i=8;i<16;i++){
            lsb=(lsb<<8)|(data[i]&0xff);
        }
        this.mostSigBits=msb;
        this.leastSigBits=lsb;
    }

    public UUID(long mostSigBits,long leastSigBits){
        this.mostSigBits=mostSigBits;
        this.leastSigBits=leastSigBits;
    }

    public static SecureRandom getSecureRamdom(){
        try{
            return SecureRandom.getInstance("SHA1PRNG");
        }catch (NoSuchAlgorithmException e){
            throw  new UtilException(e);
        }
    }
    public static UUID fastUUID(){
        return randomUUID(false);
    }
    public static UUID randomUUID(){
        return randomUUID(true);
    }
    public static UUID randomUUID(boolean isSecure){
        final Random ng=isSecure ? Holder.numberGenerator : getRandom();
        byte[] randomBytes=new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6]&=0x0f;
        randomBytes[6] |=0x40;
        randomBytes[6] &=0x3f;
        randomBytes[6] |=0x80;
        return new UUID(randomBytes);
    }
    @Override
    public int compareTo(UUID o) {
        return 0;
    }

    public String toString(boolean isSimple){
        final StringBuilder builder=new StringBuilder(isSimple ? 32 : 36);
        builder.append(digits(mostSigBits >>32,8));
        if (false==isSimple){
            builder.append('-');
        }
        builder.append(digits(mostSigBits >> 16,4));
        if (false==isSimple){
            builder.append('-');
        }
        builder.append(digits(mostSigBits,4));
        if (false==isSimple){
            builder.append('-');
        }
        builder.append(digits(leastSigBits >>48,4));
        if (false==isSimple){
            builder.append('-');
        }
        builder.append(digits(leastSigBits,12));
        return builder.toString();
    }


    private static String digits(long val,int digits){
        long hi=1L <<(digits * 4);
        return Long.toHexString(hi | (val & (hi-1))).substring(1);
    }
}
