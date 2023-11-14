package com.xzedu.article.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassName : UserInfo
 * @Description : UserInfo
 * @Author : Xxxxx
 * @Date: 2023-11-14 13:45
 */
@Data
public class UserInfo {
    private String id;// 主键

    @Pattern(regexp = "^\\S{6,11}$")
    @NotEmpty
    private String userName;// 用户名 长度6-11位

    @Pattern(regexp = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$")
    @NotEmpty
    private String pwd;// 密码必须至少包含，数字，大小写字母，特殊字符 其中三者

    @Pattern(regexp = "^[01]$")
    @NotEmpty
    private String sex;// 性别 0女 1男

    @Pattern(regexp = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$")
    @NotEmpty
    private String phone;// 手机号

    @Email
    @NotEmpty
    private String email;// 邮箱
    private String manager_flg;// 2代表管理员,1代表会员,0代表普通用户

    @URL
    private String img_url;// 头像地址
    private String crtDtime;// 注册的时间
    private String udtDtime;// 更新时间
}
