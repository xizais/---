package com.example.teamassistantbackend.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huang
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册自定义的拦截器
        registry.addInterceptor(new AuthenticationInterceptor())
//                .addPathPatterns("/**") // 拦截的路径
////                .addPathPatterns("/clientContact/**") // 拦截的路径
               .excludePathPatterns("/user/**") // 排除的路径
        ;
    }
}