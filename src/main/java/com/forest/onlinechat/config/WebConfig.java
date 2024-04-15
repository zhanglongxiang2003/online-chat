package com.forest.onlinechat.config;

import com.forest.onlinechat.inteceptors.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录注册接口不拦截
        registry.addInterceptor(tokenInterceptors)
                .excludePathPatterns(
                        "/online/user/login",
                        "/online/user/code",
                        "/online/user/register",
                        "/images/**"
                );

        //资源文件排除

    }
}
