package com.palewoods.mytaobao.dao.biz;

import com.palewoods.mytaobao.dao.IProductService;
import com.palewoods.mytaobao.daompl.ProductService;
import com.palewoods.mytaobao.model.Product;

import java.util.List;

/**
 * Created by bsl52840 on 2017/3/15.
 * 商品的管理类
 */

public class ProductManager {
    private IProductService dao = null;

    public ProductManager() {
        dao = new ProductService();
    }

    public List<Product> getAll() {
        return this.dao.getAll();
    }

    public List<Product> getProductByPager(int pageIndex, int pageSize) {
        return this.dao.getByPager(pageIndex,pageSize);
    }

    public List<Product> getProductByName(String name) {
        return this.dao.getByName(name);
    }

    public Product getProductById(int productId) {
        return this.dao.getById(productId);
    }

    public boolean AddProduct(Product p) {
        try {
            this.dao.Insert(p);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean AddProduct(int id,String name,int categoryId,int photo,double unitPrice,String note) {
        Product p = new Product(id,name,categoryId,photo,unitPrice,note);
        return this.AddProduct(p);
    }

    public boolean ModifyProduct(Product product) {
        try {
            this.dao.Modify(product);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean ModifyProduct(int id,String name,int categoryId,int photo,double unitPrice,String note) {
//        Product p = new Product(id,name,categoryId,photo,unitPrice,note);
        Product p = this.getProductById(id);
        p.setName(name);
        p.setCategoryId(categoryId);
        p.setPhoto(photo);
        p.setUnitPrice(unitPrice);
        p.setNote(note);
        return this.ModifyProduct(p);
    }

    public boolean DelProduct(int id) {
        try {
            this.dao.Del(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean DelProduct(Product p) {
        return this.DelProduct(p.getId());
    }
}
