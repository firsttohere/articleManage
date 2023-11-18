package com.xzedu.article.webmvc;

import com.xzedu.article.webmvc.interceptors.ExitSystemInterceptor;
import com.xzedu.article.webmvc.interceptors.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : WebMvcConfiguration
 * @Description : WebMvcConfiguration
 * @Author : Xxxxx
 * @Date: 2023-11-15 09:40
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Autowired
    private ExitSystemInterceptor exitSystemInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .excludePathPatterns("/user/login/**", "/user/register");
        registry.addInterceptor(exitSystemInterceptor)
                .addPathPatterns("/user/exit");
    }
}
