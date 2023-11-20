package com.xzedu.article.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzedu.article.exception.ServiceException;
import com.xzedu.article.mapper.ArticleMapper;
import com.xzedu.article.pojo.Article;
import com.xzedu.article.pojo.PageBean;
import com.xzedu.article.service.ArticleService;
import com.xzedu.article.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName : ArticleServiceImpl
 * @Description : ArticleServiceImpl
 * @Author : Xxxxx
 * @Date: 2023-11-19 12:26
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        // 获取创建的用户
        article.setCreateUser(UserUtil.getUserInfo().getId());
        articleMapper.insert(article);
    }

    @Override
    public PageBean<Article> findByPage(Integer pageNo, Integer pageSize, String categoryId, String state) {
        // 需要查询当前用户创建的，所有分类和状态为对应值的文章
        PageBean<Article> res = new PageBean<>();
        // 使用分页查询 pageHelper 需要先开启插件功能
        PageHelper.startPage(pageNo, pageSize);

        // 直接调用查询
        Page<Article> articles = (Page<Article>) articleMapper.findByPage(UserUtil.getUserInfo().getId(), categoryId, state);
        // pageHelper插件使得mapper方法的返回的运行类型为 Page
        res.setTotal((int)articles.getTotal());
        res.setItems(articles.getResult());
        return res;
    }

    @Override
    public void remove(String id) {
        articleMapper.delete(id);
    }

    @Override
    public void modify(Article article) {
        if ("已发布".equals(article.getState())) {
            throw new ServiceException("已发布的文章不允许修改");
        }
        articleMapper.update(article);
    }
}
