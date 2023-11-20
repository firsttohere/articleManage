package com.xzedu.article.controller;

import com.xzedu.article.common.Result;
import com.xzedu.article.pojo.Category;
import com.xzedu.article.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // 使用validated注解时可以指定校验的组
    public Result<String> add(@RequestBody @Validated({Category.Add.class}) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> allOwn() {
        return Result.success(categoryService.findByCreateUser());
    }


    @PutMapping
    public Result<String> modify(@RequestBody @Validated({Category.Modify.class}) Category category) {
        categoryService.modifyCategory(category);
        return Result.success();
    }

    @DeleteMapping
    public Result<String> delete(@RequestBody @Validated({Category.Delete.class}) Category category) {
        categoryService.delete(category.getId());
        return Result.success();
    }
}
