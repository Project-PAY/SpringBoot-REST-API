package com.pay.as.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    //    https://github.com/sociablesasha/BBS-SpringBoot-REST-API-JWT/blob/master/src/main/java/com/nomsa/bbs/Config/MVCConfig.java
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(createInterceptor())
                .excludePathPatterns(new String[]{
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/auth",
                        "/error/**"
                });
    }


    @Bean
    public JWTInterceptor createInterceptor() {
        return new JWTInterceptor();
    }

}
