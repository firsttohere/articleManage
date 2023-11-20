package com.xzedu.article.controller;


import com.xzedu.article.common.Result;
import com.xzedu.article.pojo.UserInfo;
import com.xzedu.article.service.UserService;
import com.xzedu.article.utils.JWTUtil;
import com.xzedu.article.utils.MD5Util;
import com.xzedu.article.utils.RedisUtil;
import com.xzedu.article.utils.UserUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : UserController
 * @Description : UserController
 * @Author : Xxxxx
 * @Date: 2023-11-14 13:50
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
@Validated
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

    /**
    * @Title: 使用账号密码登录
    * @Param: userName用户名 pwd密码
    * @description: 登录功能，如果用户名密码输入正确，登录成功。登录成功后生成jwt（token）响应给前端，之后访问主服务时，需要携带token
    * @author: xiangzhou
    * @date: 2023/11/15 8:49
    * @return: com.xzedu.article.common.Result<java.lang.String>
    */
    @GetMapping("/login/{userName}/{pwd}")
    public Result<Map<String, Object>> login (@PathVariable("userName") String userName,
                                 @PathVariable("pwd") String pwd) {
        UserInfo u = userService.findByUserName(userName);
        HashMap<String, Object> map = new HashMap<>();
        if (u == null) {
            map.put("msg", "系统没有此用户, 请先注册");
            return Result.failure(map);
        }
        if (!u.getPwd().equals(MD5Util.encode(pwd))) {
            map.put("msg", "密码错误");
            return Result.failure(map);
        }
        // 登录成功，生成token
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("id", u.getId());
        userInfoMap.put("userName", userName);
        userInfoMap.put("sex", u.getSex());
        userInfoMap.put("phone", u.getPhone());
        userInfoMap.put("email", u.getEmail());
        userInfoMap.put("managerFlg", u.getManagerFlg());
        userInfoMap.put("imgUrl", u.getImgUrl());
//        userInfoMap.put("crtDtime", u.getCrtDtime().toString());
//        userInfoMap.put("udtDtime", u.getUdtDtime().toString());
        String token = JWTUtil.genToken("user", userInfoMap);
        map.put("token", token);
        // 将token放在redis中
        RedisUtil.putToken(userName, token);
        map.put("userInfo", u);
        return Result.success(map);
    }


    @GetMapping("/info")
    @Deprecated
    public Result<UserInfo> userInfo() {
        return Result.success(userService.getUserInfo());
    }

    @GetMapping("/exit")
    public Result<String> exit() {
        // 使得token失效
        RedisUtil.removeToken(UserUtil.getUserInfo().getUserName());
        return Result.success();
    }

    // 修改用户的基本信息接口，允许修改用户的手机号 邮箱
    @PutMapping("/upBase/{phone}/{email}")
    public Result<Map<String, Object>> upBase(@PathVariable("phone") @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$")
                                                          String phone,
                                              @PathVariable("email") @Email String email) {
        Map<String, Object> map = new HashMap<>();
        // 更新用户信息
        userService.updatePhoneAndEmail(phone, email);
        // 生成新的token和用户信息返回给前端
        UserInfo u = userService.findByUserName();
        map.put("userInfo", u);
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("id", u.getId());
        userInfoMap.put("userName", u.getUserName());
        userInfoMap.put("sex", u.getSex());
        userInfoMap.put("phone", u.getPhone());
        userInfoMap.put("email", u.getEmail());
        userInfoMap.put("managerFlg", u.getManagerFlg());
        userInfoMap.put("imgUrl", u.getImgUrl());
//        userInfoMap.put("crtDtime", u.getCrtDtime().toString());
//        userInfoMap.put("udtDtime", u.getUdtDtime().toString());
        String token = JWTUtil.genToken("user", userInfoMap);
        map.put("token", token);
        RedisUtil.putToken(u.getUserName(), token);
        return Result.success(map);
    }

    // 更新用户头像接口
    @PutMapping("/upImg")
    public Result<Map<String, Object>> updateImg(@RequestParam("url") @URL String url) {
        Map<String, Object> map = new HashMap<>();
        // 更新用户信息
        userService.updateUrl(url);
        // 生成新的token和用户信息返回给前端
        UserInfo u = userService.findByUserName();
        map.put("userInfo", u);
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("id", u.getId());
        userInfoMap.put("userName", u.getUserName());
        userInfoMap.put("sex", u.getSex());
        userInfoMap.put("phone", u.getPhone());
        userInfoMap.put("email", u.getEmail());
        userInfoMap.put("managerFlg", u.getManagerFlg());
        userInfoMap.put("imgUrl", u.getImgUrl());
//        userInfoMap.put("crtDtime", u.getCrtDtime());
//        userInfoMap.put("udtDtime", u.getUdtDtime());
        String token = JWTUtil.genToken("user", userInfoMap);
        map.put("token", token);
        RedisUtil.putToken(u.getUserName(), token);
        return Result.success(map);
    }

    // 更新用户密码接口
    @PostMapping("/upPwd/{oldPwd}/{newPwd}/{renewPwd}")
    public Result<String> updatePassword(@PathVariable("oldPwd") String oldPwd,
                                         @PathVariable("newPwd") @Pattern(regexp = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$")
                                                     String newPwd,
                                         @PathVariable("renewPwd") String renewPwd) {
        UserInfo u = userService.findByUserName();
        // 如果原密码和 用户现在的密码不一致,拒绝修改
        if (!u.getPwd().equals(MD5Util.encode(oldPwd))) {
            return Result.failure("原密码不正确");
        }
        // 两次输入的新密码必须一致
        if (!newPwd.equals(renewPwd)) {
            return Result.failure("两次输入的新密码不一致");
        }
        // 更新密码
        userService.updatePwd(newPwd);
        // 把token清除
        RedisUtil.removeToken(u.getUserName());
        return Result.success();
    }
}
