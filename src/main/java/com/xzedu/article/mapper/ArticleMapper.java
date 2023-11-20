package com.xzedu.article.mapper;

import com.xzedu.article.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName : ArticleMapper
 * @Description : ArticleMapper
 * @Author : Xxxxx
 * @Date: 2023-11-19 12:27
 */
@Mapper
public interface ArticleMapper {
    @Insert("insert into article (title, content, cover_img, state, category_id, create_user, create_time) " +
            "values (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now())")
    void insert(Article article);

    List<Article> findByPage(Integer id, String categoryId, String state);

    @Delete("delete from article where id = #{id}")
    void delete(String id);

    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, update_time = now()" +
            "where id = #{id}")
    void update(Article article);
}
