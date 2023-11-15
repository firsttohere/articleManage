package com.xzedu.article.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @ClassName : JWTUtilTest
 * @Description : JWTUtilTest
 * @Author : Xxxxx
 * @Date: 2023-11-15 09:17
 */
@SpringBootTest
public class JWTUtilTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testJWTPropertiesInject() {
        System.out.println("=======================");
        JWTUtil jwtUtil = (JWTUtil) applicationContext.getBean("JWTUtil");
        //System.out.println(jwtUtil.encodeStr);
    }
}
