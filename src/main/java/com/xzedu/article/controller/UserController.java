package com.xzedu.article.controller;

import com.xzedu.article.common.Result;
import com.xzedu.article.pojo.UserInfo;
import com.xzedu.article.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : UserController
 * @Description : UserController
 * @Author : Xxxxx
 * @Date: 2023-11-14 13:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
    * @Title: 注册功能
    * @Param: 用户名，密码
    * @description: 用户名长度限制6-11位 密码限制数字，字母，特殊符号必须都存在
    * @author: xiangzhou
    * @date: 2023/11/14 13:51
    * @return: 注册成功或者失败
    */
    @PostMapping("/register")
    public Result<String> register (@RequestBody @Validated UserInfo uI) {

        // 1.校验用户名是否已被占用
        UserInfo user1 = userService.findByUserName(uI.getUserName());
        if (user1 != null) {// 已经被占用了
            return Result.failure("用户名重复");
        }

        // 2.验证手机号是否重复
        UserInfo user2 = userService.findByPhone(uI.getPhone());
        if (user2 != null) {
            return Result.failure("当前手机号已经注册过");
        }

        // 3.验证邮箱是否重复
        UserInfo user3 = userService.findByEmail(uI.getEmail());
        if (user3 != null) {
            return Result.failure("当前邮箱已经注册过");
        }

        // 4.保存用户信息
        userService.registerUserInfo(uI);
        return Result.success();
    }
}
