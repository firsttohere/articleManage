package com.xzedu.article.webmvc.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xzedu.article.utils.RedisUtil;
import com.xzedu.article.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName : LoginCheckInterceptor
 * @Description : LoginCheckInterceptor
 * @Author : Xxxxx
 * @Date: 2023-11-15 09:40
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Value("${jwt.encodeStr}")
    private String encodeStr;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 前置处理中，需要判断用户是否登录，如果用户登录过，证明其请求头中包含了Authorization并且值是有效的
        String token = request.getHeader("Authorization");
        if (StringUtils.hasLength(token)) {
            // 验签
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(encodeStr)).build();
            DecodedJWT decodedJWT = null;
            try {
                decodedJWT = jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {// 验签异常说明，token被篡改或者token过期 响应状态码为 401
                response.setStatus(401);
                return false;
            }
            // 验签通过后，为了方便，把token中携带的用户信息存储到ThreadLocal中
            Map<String, Object> user = decodedJWT.getClaim("user").asMap();
            String nowToken = RedisUtil.getToken(user.get("userName").toString());// 用户现在使用的token
            // 某一刻，一个用户只允许有一个token有效
            if (!token.equals(nowToken)) {//token已经被启=弃用了
                response.setStatus(401);
                return false;
            }
            UserUtil.putUserInfo(user);
            return true;
        }
        response.setStatus(401);
        return false;
    }
}
