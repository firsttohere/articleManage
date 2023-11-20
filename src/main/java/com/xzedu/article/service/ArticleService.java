package com.xzedu.article.service;

import com.xzedu.article.pojo.Article;
import com.xzedu.article.pojo.PageBean;

/**
 * @ClassName : ArticleService
 * @Description : ArticleService
 * @Author : Xxxxx
 * @Date: 2023-11-19 12:26
 */
public interface ArticleService {
    void addArticle(Article article);

    PageBean<Article> findByPage(Integer pageNo, Integer pageSize, String categoryId, String state);

    void remove(String id);

    void modify(Article article);
}
