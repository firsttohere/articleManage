package com.xzedu.article.utils;

import com.xzedu.article.pojo.UserInfo;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @ClassName : UserUtil
 * @Description : 用户工具类，用来存储本次请求（一个线程）中客户的信息
 * @Author : Xxxxx
 * @Date: 2023-11-15 09:53
 */
public class UserUtil {

    private static final ThreadLocal<UserInfo> localUsers = new ThreadLocal<>();

    public static void putUserInfo(Map<String, Object> userInfoMap) {
        putUserInfo(mapToUser(userInfoMap));
    }

    /***
    * @Title: putUserInfo
    * @Param: [userInfo]
    * @description: 将一个用户对象放在当前线程对应的ThreadLocalMap中
    * @author: xiangzhou
    * @date: 2023/11/15 9:56
    * @return: void
    */
    public static void putUserInfo(UserInfo userInfo) {
        localUsers.set(userInfo);
    }

    public static UserInfo getUserInfo() {
        return localUsers.get();
    }

    private static UserInfo mapToUser(Map<String, Object> map) {
        UserInfo userInfo = new UserInfo();
        Field field = null;
        for (Map.Entry<String, Object> property : map.entrySet()) {
            try {
                field = UserInfo.class.getDeclaredField(property.getKey());
                field.setAccessible(true);
                field.set(userInfo, property.getValue());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // 没这个字段就不处理
            }
        }
        return userInfo;
    }
}
