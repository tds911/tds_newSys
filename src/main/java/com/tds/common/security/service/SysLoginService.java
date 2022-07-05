package com.tds.common.security.service;

import com.tds.common.constant.Constants;
import com.tds.common.exception.CustomException;
import com.tds.common.exception.user.UserPasswordNotMatchException;
import com.tds.common.manager.factory.AsyncFactory;
import com.tds.common.manager.factory.AsyncManager;
import com.tds.common.redis.RedisCache;
import com.tds.common.utils.MessageUtils;

import com.tds.project.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    public String login(String username, String password, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        //用户验证
        Authentication authentication = null;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println(authentication);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        return tokenService.createToken(userEntity);
    }
}
