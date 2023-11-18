package com.xzedu.article.mapper;

import com.xzedu.article.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}
