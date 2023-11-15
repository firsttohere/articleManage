package com.xzedu.article.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName : JWTUtil
 * @Description : JWTUtil
 * @Author : Xxxxx
 * @Date: 2023-11-15 09:00
 */
@Component("JWTUtil")
public class JWTUtil {

    @Value("${jwt.encodeStr}")
    private String encodeStr;

    @Value("${jwt.expireTime}")
    private Integer expireTime;

    private static JWTUtil JWT_UTIL = null;

    public JWTUtil() {
        JWT_UTIL = this;
    }

    /**
    * @Title: genToken 获取token
    * @Param: [payLoad, expireTime] 载荷与过期时间 过期时间的单位默认就是秒
    * @author: xiangzhou
    * @date: 2023/11/15 9:03
    * @return: java.lang.String
    */
    private String genTokenInner(String payLoadName, Map<String, Object> payLoad) {
        return JWT.create()
                .withClaim(payLoadName, payLoad)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime * 1000))
                .sign(Algorithm.HMAC256(encodeStr));
    }

    public static String genToken (String payLoadName, Map<String, Object> payLoad) {
        return JWT_UTIL.genTokenInner(payLoadName, payLoad);
    }
}
