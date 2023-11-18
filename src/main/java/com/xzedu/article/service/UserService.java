package com.xzedu.article.service;

import com.xzedu.article.exception.ServiceException;
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

    UserInfo findByUserName();

    // 注册用户信息
    void registerUserInfo(UserInfo userInfo);

    UserInfo findByPhone(String phone);

    UserInfo findByEmail(String email);

    UserInfo getUserInfo();

    void updatePhoneAndEmail(String phone, String email);

    void updateUrl(String url);

    void updatePwd(String newPwd);
}
