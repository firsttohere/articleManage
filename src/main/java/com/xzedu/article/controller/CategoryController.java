package com.xzedu.article.controller;

import com.xzedu.article.common.Result;
import com.xzedu.article.pojo.Category;
import com.xzedu.article.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName : CategoryController
 * @Description : 文章分类的controller
 * @Author : Xxxxx
 * @Date: 2023-11-18 20:32
 */
@RequestMapping("/category")
@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result<String> add(@RequestBody @Validated Category category) {
        categoryService.add(category);
        return Result.success();
    }
}
