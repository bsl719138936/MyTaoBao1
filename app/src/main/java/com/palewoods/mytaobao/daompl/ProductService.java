package com.palewoods.mytaobao.daompl;

import com.palewoods.mytaobao.R;
import com.palewoods.mytaobao.dao.IProductService;
import com.palewoods.mytaobao.model.Category;
import com.palewoods.mytaobao.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bsl52840 on 2017/3/15.
 * 商品实现
 */

public class ProductService implements IProductService{
    /*模拟数据*/
    private Product[] products = {
            new Product(136101,"三星",1, R.drawable.ic_136101,1569.00,"网络类型：3G  操作系统：Android"),
            new Product(136102,"诺基亚",1, R.drawable.ic_136102,1485.00,"网络类型：3G  操作系统：Android"),
            new Product(136103,"联想",1, R.drawable.ic_136103,1569.00,"网络类型：3G  操作系统：Android"),
            new Product(136104,"小米",1, R.drawable.ic_136104,1566.00,"网络类型：3G  操作系统：Android"),
            new Product(136105,"华为",1, R.drawable.ic_136105,1559.00,"网络类型：3G  操作系统：Android"),
            new Product(136106,"酷派",1, R.drawable.ic_136106,1659.00,"网络类型：3G  操作系统：Android"),
            new Product(136107,"乐视",1, R.drawable.ic_136107,1539.00,"网络类型：3G  操作系统：Android"),
            new Product(136108,"苹果",1, R.drawable.ic_136108,1599.00,"网络类型：3G  操作系统：Android"),
            new Product(136109,"ViVO",1, R.drawable.ic_136109,1549.00,"网络类型：3G  操作系统：Android"),
            new Product(136110,"OPPO",1, R.drawable.ic_136110,1569.00,"网络类型：3G  操作系统：Android")
    };

    private List<Product> ps = null;

    public ProductService() {
        ps = new ArrayList<Product>();
        for (Product P :
                products) {
            ps.add(P);
        }
    }

    /*获取全部商品*/
    @Override
    public List<Product> getAll() {
        return this.ps;
    }

    /*按页存取数据
    * pageIndex 页面索引，即代表第几页（首页为0）；
    * pageSize 每页显示的条目数；
    * 返回每页的数据
    * */
    @Override
    public List<Product> getByPager(int pageIndex, int pageSize) {
        int totalCount = products.length;//总条目数
        int pageCount = 1;//总页数

        if (pageIndex < 0) {
            //页面索引小于第一页时，固定在第一页
            pageIndex = 0;
        }

        /*获取页数*/
        if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize;
        } else {
            pageCount = (totalCount / pageSize) + 1;
        }

        if (pageIndex > pageCount - 1) {
            //页面索引大于最后一页时，即数据已经加载完了，返回null
//            pageIndex = pageCount - 1;
            return null;
        }

        int endIndex = (pageIndex + 1) * pageSize;//结束的索引
        if (endIndex > totalCount) {//如果结束的索引大于实际数据的条目数，则把总条数赋值给endIndex
            endIndex = totalCount;
        }

        Object[] source = this.ps.toArray();//将List中的数据转化成Object数组，
        // toArray()这个函数返回值就是Object类型的数组

        List<Product> result = new ArrayList<Product>();//分页显示的数组
        for (int i = pageIndex * pageSize; i < endIndex; i++) {
            //定义每页显示那几条，第一页0、1、2，第二页3、4、5 。。。。
            result.add((Product) source[i]);//将上面的Object类型的数组强制转换成Product类型，放入result中
        }
        return result;
    }

    /*通过Id获取商品*/
    @Override
    public Product getById(int productId) {
        for (Product P :
                this.ps) {
            if (P.getId() == productId) {
                return P;
            }
        }
        return null;
    }

    /*通过名称模糊查询*/
    @Override
    public List<Product> getByName(String name) {
        List<Product> result = new ArrayList<Product>();
        for (Product P :
                this.ps) {
            if (P.getName().indexOf(name) != -1) {
                result.add(P);
            }
        }
        return result;
    }

    /*添加商品*/
    @Override
    public void Insert(Product product) {
        this.ps.add(product);
    }

    /*修改商品信息*/
    @Override
    public void Modify(Product product) {
        for (int i = 0; i < this.ps.size(); i++) {
            if (product.getId() == ps.get(i).getId()) {
                //找到对应商品，使用传入的参数替换对应数据
                ps.set(i,product);//指定替换的内容
                break;
            }
        }
    }

    /*删除商品*/
    @Override
    public void Del(int id) {
        Product p = this.getById(id);
        if (p != null) {
            this.ps.remove(p);
        }
    }
}
