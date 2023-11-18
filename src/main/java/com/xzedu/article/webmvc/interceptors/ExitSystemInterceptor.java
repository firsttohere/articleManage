package com.xzedu.article.webmvc.interceptors;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : ExitSystemInterceptor
 * @Description : ExitSystemInterceptor
 * @Author : Xxxxx
 * @Date: 2023-11-18 14:15
 */
@Component
public class ExitSystemInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        // 将token失效即可
        // TODO 这里可以让这个token加入黑名单表，在验证token的时候不仅需要token本身没过期，还需要满足不在黑名单内
        return true;
    }
}
