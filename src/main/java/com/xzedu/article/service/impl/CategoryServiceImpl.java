package com.xzedu.article.service.impl;

import com.xzedu.article.mapper.CategoryMapper;
import com.xzedu.article.pojo.Category;
import com.xzedu.article.service.CategoryService;
import com.xzedu.article.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName : CategoryServiceImpl
 * @Description : CategoryServiceImpl
 * @Author : Xxxxx
 * @Date: 2023-11-18 20:34
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        category.setCreateUser(UserUtil.getUserInfo().getId());
        categoryMapper.add(category);
    }
}
