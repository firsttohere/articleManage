package com.xzedu.article.service.impl;

import com.xzedu.article.exception.ServiceException;
import com.xzedu.article.mapper.UserMapper;
import com.xzedu.article.pojo.UserInfo;
import com.xzedu.article.service.UserService;
import com.xzedu.article.utils.MD5Util;
import com.xzedu.article.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName : UserServiceImpl
 * @Description : UserServiceImpl
 * @Author : Xxxxx
 * @Date: 2023-11-14 14:11
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo findByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public void registerUserInfo(UserInfo userInfo) {
        // md5加密
        userInfo.setPwd(MD5Util.encode(userInfo.getPwd()));
        userMapper.insertUserInfo(userInfo);
    }

    @Override
    public UserInfo findByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public UserInfo findByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public UserInfo getUserInfo() {
        return UserUtil.getUserInfo();
    }

    @Override
    public void updatePhoneAndEmail(String phone, String email) {
        userMapper.updatePhoneAndEmail(UserUtil.getUserInfo().getUserName(), phone, email);
    }

    @Override
    public UserInfo findByUserName(){
        return findByUserName(UserUtil.getUserInfo().getUserName());
    }

    @Override
    public void updateUrl(String url) {
        userMapper.updateUrl(UserUtil.getUserInfo().getUserName(), url);
    }

    @Override
    public void updatePwd(String newPwd) {
        userMapper.updatePwd(UserUtil.getUserInfo().getUserName(), MD5Util.encode(newPwd));
    }
}
