package com.tds.project.service;



import com.tds.project.domain.UserEntity;

public interface AuthService {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    UserEntity findByUsername(String username);


    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    UserEntity findByToken(String token);
}
