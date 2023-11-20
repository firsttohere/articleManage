package com.xzedu.article.service;

import com.xzedu.article.pojo.Category;

import java.util.List;

/**
 * @ClassName : CategoryService
 * @Description : CategoryService
 * @Author : Xxxxx
 * @Date: 2023-11-18 20:34
 */
public interface CategoryService {
    void add(Category category);

    List<Category> findByCreateUser();

    void modifyCategory(Category category);

    void delete(Integer id);

}
