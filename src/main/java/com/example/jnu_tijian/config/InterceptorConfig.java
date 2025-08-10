package com.example.jnu_tijian.config;

import com.example.jnu_tijian.interceptor.JWTInterceptor;
import com.example.jnu_tijian.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(new JWTInterceptor())
                //order代表执行优先级 越小越先执行
                .addPathPatterns("/**").order(10) // 拦截所有请求路径
                .excludePathPatterns("/userLogin", "/userRegister","/alipay/**","/**"); // 排除某些路径

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**") // 拦截所有请求路径
                .excludePathPatterns("/userLogin", "/userRegister","/alipay/**","/**").order(20); // 排除某些路径
    }


}