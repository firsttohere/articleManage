package com.xzedu.article.controller;

import com.xzedu.article.common.Result;
import com.xzedu.article.pojo.Article;
import com.xzedu.article.pojo.PageBean;
import com.xzedu.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName : ArticleController
 * @Description : ArticleController
 * @Author : Xxxxx
 * @Date: 2023-11-15 10:20
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/all")
    public Result<String> findOwnArticles() {
        return Result.success("true");
    }

    // 新增文章
    @PostMapping
    public Result<String> addArticle(@RequestBody @Validated({Article.Add.class}) Article article) {
        articleService.addArticle(article);
        return Result.success();
    }

    // 删除文章
    @DeleteMapping
    public Result<String> remove(@RequestParam("id") String id) {
        articleService.remove(id);
        return Result.success();
    }

    // 修改文章 已发布的文章不允许修改
    @PutMapping
    public Result<String> modify(@RequestBody @Validated({Article.Modify.class}) Article article) {
        articleService.modify(article);
        return Result.success();
    }

    // 查询个人文章列表 分页
    @GetMapping
    public Result<PageBean<Article>> getArticles(@RequestParam("pageNo") Integer pageNo,
                                                 @RequestParam("pageSize") Integer pageSize,
                                                 @RequestParam(value = "categoryId", required = false) String categoryId,
                                                 @RequestParam(value = "state", required = false) String state) {
        return Result.success(articleService.findByPage(pageNo, pageSize, categoryId, state));
    }
}
