package com.xzedu.article.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName : MD5Util
 * @Description : MD5Util
 * @Author : Xxxxx
 * @Date: 2023-11-14 14:35
 */
public class MD5Util {

    private static final MessageDigest messageDigest;

    static {
        MessageDigest messageDigestTemp = null;
        try {
            messageDigestTemp = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MessageDigest对象获取失败");
        }
        messageDigest = messageDigestTemp;
    }

    public static String encode(String s) {
        byte[] decodedBytes = messageDigest.digest(s.getBytes());// 16字节的字节数组
        // 转换为32位的16进制的字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : decodedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
