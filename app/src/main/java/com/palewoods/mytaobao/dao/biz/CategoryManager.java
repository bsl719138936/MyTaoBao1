package com.palewoods.mytaobao.dao.biz;

import com.palewoods.mytaobao.dao.ICategoryService;
import com.palewoods.mytaobao.daompl.CategoryService;
import com.palewoods.mytaobao.model.Category;

import java.util.List;

/**
 * Created by bsl52840 on 2017/3/15.
 * 类别的管理类
 */

public class CategoryManager {
    private ICategoryService dao;

    public CategoryManager() {
        dao = new CategoryService();
    }

    public List<Category> getAllCategory() {
        return dao.getAllCategory();
    }

    public Category getCategoryById(int categoryId) {
        return dao.getCategoryById(categoryId);
    }
}
