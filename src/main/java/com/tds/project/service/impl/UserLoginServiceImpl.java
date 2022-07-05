package com.tds.project.service.impl;


import com.tds.project.domain.User;
import com.tds.project.mapper.UserLoginMapper;
import com.tds.project.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginMapper userLoginMapper;
    
    @Override
    public User findByUsername(String username) {
      //  System.out.println(userLoginMapper.findByUsername(username));
        return userLoginMapper.findByUsername(username);
    }
}
