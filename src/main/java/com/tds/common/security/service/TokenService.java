package com.tds.common.security.service;

import com.tds.common.constant.Constants;
import com.tds.common.redis.RedisCache;

import com.tds.common.utils.IdUtils;
import com.tds.common.utils.StringUtils;

import com.tds.project.domain.UserEntity;
import com.tds.project.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TokenService {
    @Value("${token.header}")
    private String header;
    @Value("${token.secret}")
    private String secret;
    @Value("${token.expireTime}")
    private Integer expireTime;

    @Autowired
    private RedisCache redisCache;
    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    public UserEntity getLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = parseToken(token);
            String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
            String usertoken = getTokenKey(uuid);
            UserEntity user = redisCache.getCacheObject(usertoken);
            return user;
        }
        return null;
    }

    /**
     * 设置用户身份信息
     * @param user
     */
    public void setLoginUser(UserEntity user){
        if (StringUtils.isNotNull(user)&& StringUtils.isNotEmpty(user.getToken())){
            refreshToken(user);
        }
    }

    /**
     * 获取请求token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }

    /**
     * 从令牌中获取数据声明
     * @param token
     * @return
     */
    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 创建令牌
     * @param userEntity
     * @return
     */
    public String createToken(UserEntity userEntity) {
        String token = IdUtils.fastUUID();
        userEntity.setToken(token);
        refreshToken(userEntity);

        Map<String,Object> claims=new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY,token);
        return createToken(claims);


    }

    public void setUserAgent(UserEntity userEntity) {

    }

    /**
     * 刷新令牌有效期
     * @param userEntity
     */
    public void refreshToken(UserEntity userEntity) {
        userEntity.setLoginTime(System.currentTimeMillis());
        userEntity.setExpireTime(userEntity.getLoginTime() + expireTime * MILLIS_MINUTE);
        String userKey=getTokenKey(userEntity.getToken());
        redisCache.setCacheObject(userKey,userEntity,expireTime, TimeUnit.MINUTES);

    }

    /**
     * 从书籍声明生成令牌
     * @param claims
     * @return
     */
    public String createToken(Map<String,Object>claims){
        String token=Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret).compact();
        return token;
    }

    /**
     * 验证令牌有效期，自动刷新
     * @param userEntity
     */
    public void verifyToken(UserEntity userEntity){
        long expireTime=userEntity.getExpireTime();
        long currentTime=System.currentTimeMillis();
        if (expireTime-currentTime<=MILLIS_MINUTE_TEN){
            refreshToken(userEntity);
        }
    }

    /**
     * 删除用户身份
     * @param token
     */
    public void delLogingUser(String token){
        if (StringUtils.isNotEmpty(token)){
            String userKey=getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 从令牌中获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){
        Claims claims=parseToken(token);
        return claims.getSubject();
    }


}
