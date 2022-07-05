package com.tds.common.utils;

import com.tds.common.constant.HttpStatus;
import com.tds.common.exception.CustomException;

import com.tds.project.domain.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {
    /**
     * 获取Authentication
     * @return
     */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public  static String getUsername(){
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e){
            throw new CustomException("获取用户异常", HttpStatus.UNAUTHORIZED);
        }
    }
    public static UserEntity getLoginUser(){
        try{
            return (UserEntity) getAuthentication().getPrincipal();
        }
        catch (Exception e){
            throw new CustomException("获取用户信息异常",HttpStatus.UNAUTHORIZED);
        }
    }
    public static String encryptPassword(String password){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    public static boolean matchesPassword(String rawPassword,String encodedPassword){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword,encodedPassword);
    }
    public static boolean isAdmin(Long userId){
        return userId !=null && 1L==userId;
    }
}
