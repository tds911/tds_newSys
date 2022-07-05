package com.tds.common.security.filter;

import com.tds.common.security.service.TokenService;
import com.tds.common.utils.SecurityUtils;
import com.tds.common.utils.StringUtils;

import com.tds.project.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        UserEntity userEntity = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(userEntity) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
            tokenService.verifyToken(userEntity);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
