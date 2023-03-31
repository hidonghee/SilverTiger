package com.example.silvertiger.util;

import com.example.silvertiger.jwt.JwtTokenProvider;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    private final JwtTokenProvider jwtTokenProvider;
    public JwtUtil(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwt = (String) authentication.getCredentials();
        return jwtTokenProvider.getUserPk(jwt);
    }

}
