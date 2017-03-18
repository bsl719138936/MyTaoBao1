package com.palewoods.mytaobao.dao;

import com.palewoods.mytaobao.model.Category;

import java.util.List;

/**
 * Created by bsl52840 on 2017/3/15.
 * 类别对应接口
 */

public interface ICategoryService {
    /*得到系统所有类别
     * 返回所有类别
      * */
    public List<Category> getAllCategory();

    /*根据类别Id获取类别实体
    * categoryId 类别Id
    * 返回类别实体
    * */
    public Category getCategoryById(int categoryId);
}
