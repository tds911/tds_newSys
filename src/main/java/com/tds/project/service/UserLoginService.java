package com.tds.project.service;


import com.tds.project.domain.User;


public interface UserLoginService {
    User findByUsername(String username);
}
