package com.palewoods.mytaobao.dao;

import com.palewoods.mytaobao.model.Product;

import java.util.List;

/**
 * Created by bsl52840 on 2017/3/15.
 * 产品对应接口
 */

public interface IProductService {
    /*得到商品*/
    public List<Product> getAll();
    /*分页获取商品，参数1：页数  参数2：每页显示的条数*/
    public List<Product> getByPager(int pageIndex,int pageSize);
    /*通过Id获取实体*/
    public Product getById(int productId);
    /*通过name进行模糊查询*/
    public List<Product> getByName(String name);
    /*插入Product*/
    public void Insert(Product product);
    /*修改Product信息*/
    public void Modify(Product product);
    /*删除Product*/
    public void Del(int id);
}
