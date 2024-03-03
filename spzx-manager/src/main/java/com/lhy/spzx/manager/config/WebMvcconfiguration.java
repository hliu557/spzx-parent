package com.lhy.spzx.manager.config;


import com.lhy.spzx.manager.interceptor.LoginAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class WebMvcconfiguration implements WebMvcConfigurer {

    private LoginAuthInterceptor loginAuthInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*");                // 允许所有的请求头
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/system/index/login","/admin/system/index/generateValidateCode");
    }

    @Autowired
    public void setLoginAuthInterceptor(LoginAuthInterceptor loginAuthInterceptor) {
        this.loginAuthInterceptor = loginAuthInterceptor;
    }
}
