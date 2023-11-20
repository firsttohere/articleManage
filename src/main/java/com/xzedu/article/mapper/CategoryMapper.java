package com.xzedu.article.mapper;

import com.xzedu.article.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName : CategoryMapper
 * @Description : CategoryMapper
 * @Author : Xxxxx
 * @Date: 2023-11-18 20:35
 */
@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name, category_alias, create_user, create_time) values " +
            "(#{categoryName}, #{categoryAlias}, #{createUser}, now())")
    void add(Category category);

    @Select("select * from category where create_user = #{id}")
    List<Category> getByCreateUser(Integer id);

    @Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = now()" +
            "where id = #{id}")
    void modify(Category category);

    @Delete("delete from category where id = #{id}")
    void delete(Integer id);

}
