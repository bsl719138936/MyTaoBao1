package com.palewoods.mytaobao.model;

/**
 * Created by bsl52840 on 2017/3/15.
 * 类别实体
 */

public class Category {
    private int categoryId;//类型id
    private String categoryName;//类型名称

    public Category() {
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
