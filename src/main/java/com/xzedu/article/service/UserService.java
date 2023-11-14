package com.xzedu.article.service;

import com.xzedu.article.pojo.UserInfo;

/**
 * @ClassName : UserService
 * @Description : UserService
 * @Author : Xxxxx
 * @Date: 2023-11-14 14:10
 */
public interface UserService {
    // 跟据用户名查询用户是否存在
    UserInfo findByUserName(String userName);

    // 注册用户信息
    void registerUserInfo(UserInfo userInfo);

    UserInfo findByPhone(String phone);

    UserInfo findByEmail(String email);
}
