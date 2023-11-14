package com.xzedu.article.mapper;

import com.xzedu.article.pojo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName : UserMapper
 * @Description : UserMapper
 * @Author : Xxxxx
 * @Date: 2023-11-14 14:19
 */
@Mapper
public interface UserMapper {
    @Select("select * from user_info where user_name = #{userName}")
    UserInfo selectByUserName(String userName);

    @Insert("insert into user_info(user_name, pwd, sex, phone, email, crt_dtime) values (#{userName}, #{pwd}, #{sex}, #{phone}, #{email}, now())")
    void insertUserInfo(UserInfo userInfo);

    @Select("select * from user_info where phone = #{phone}")
    UserInfo selectByPhone(String phone);

    @Select("select * from user_info where email = #{email}")
    UserInfo selectByEmail(String email);
}
