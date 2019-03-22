package com.wrapsody.demo;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    private RequestInterceptor interceptor;

    public MvcConfig(RequestInterceptor interceptor) {
        this.interceptor = interceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("*");
    }
}
