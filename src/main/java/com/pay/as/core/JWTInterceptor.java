package com.pay.as.core;

import com.pay.as.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JWTInterceptor.class);
    private static final String HEADER = "PAY-Auth-Token";

    @Autowired
    JWTService jwtService;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("In Interceptor");

        final String token = request.getHeader(HEADER);


        return false;
    }

}
