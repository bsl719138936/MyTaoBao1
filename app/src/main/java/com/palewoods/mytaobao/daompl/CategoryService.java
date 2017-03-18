package com.palewoods.mytaobao.daompl;

import com.palewoods.mytaobao.dao.ICategoryService;
import com.palewoods.mytaobao.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bsl52840 on 2017/3/15.
 * 类别实现
 */

public class CategoryService implements ICategoryService{

    List<Category> categories;

    @Override
    public List<Category> getAllCategory() {
        categories = new ArrayList<Category>();
        categories.add(new Category(1,"手机"));
        categories.add(new Category(2,"电器"));
        categories.add(new Category(3,"服装"));
        categories.add(new Category(4,"图书"));
        return categories;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        if (categories != null) {
            return this.categories.get(categoryId - 1);
        } else {
            return null;
        }
    }
}
