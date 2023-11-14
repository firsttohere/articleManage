package com.xzedu.article.utils;

import org.junit.jupiter.api.Test;

/**
 * @ClassName : MD5UtilTest
 * @Description : MD5UtilTest
 * @Author : Xxxxx
 * @Date: 2023-11-14 14:44
 */
public class MD5UtilTest {
    @Test
    public void testEncodeMD5() {
        String pwd = "123456";
        System.out.println(MD5Util.encode(pwd));
    }
}
