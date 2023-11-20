package com.xzedu.article.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ClassName : RedisUtil
 * @Description : RedisUtil
 * @Author : Xxxxx
 * @Date: 2023-11-19 16:51
 */
@Component
public class RedisUtil {
    private static RedisUtil redisUtil;

    public RedisUtil() {
        redisUtil = this;
    }

    @Autowired
    private StringRedisTemplate srt;

    private void putTokenIn(String userName, String token) {
        srt.opsForHash().put("tokens", userName, token);
    }

    public static void putToken (String userName, String token) {
        redisUtil.putTokenIn(userName, token);
    }

    private String getTokenIn(String userName) {
        Object token = srt.opsForHash().get("tokens", userName);
        return token == null ? "" : token.toString();
    }

    public static String getToken(String userName) {
        return redisUtil.getTokenIn(userName);
    }

    public void removeTokenIn(String userName) {
        srt.opsForHash().delete("tokens", userName);
    }

    public static void removeToken(String userName) {
        redisUtil.removeTokenIn(userName);
    }
}
