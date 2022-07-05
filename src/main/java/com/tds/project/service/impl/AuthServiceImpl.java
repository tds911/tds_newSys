package com.tds.project.service.impl;




import com.tds.project.domain.UserEntity;
import com.tds.project.mapper.UserRepositoryMapper;
import com.tds.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepositoryMapper userRepository;

    public AuthServiceImpl(UserRepositoryMapper userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserEntity findByUsername(String username) {
        UserEntity byUsername = userRepository.findByUsername(username);
        System.out.println(byUsername);
        return userRepository.findByUsername(username);

    }





    @Override
    public UserEntity findByToken(String token) {
        return userRepository.findByToken(token);
    }
}
