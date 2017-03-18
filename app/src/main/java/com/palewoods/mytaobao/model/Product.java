package com.palewoods.mytaobao.model;

import com.palewoods.mytaobao.R;

/**
 * Created by bsl52840 on 2017/3/15.
 * 产品实体
 */

public class Product {
    private int id;//产品id
    private String name;//产品名称
    private int categoryId;//产品类型
    private int photo;//产品图片
    private double unitPrice;//产品价格
    private String note;//产品备注

    public Product() {
    }

    public Product(int id, String name, int categoryId, int photo, double unitPrice, String note) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.photo = photo;
        this.unitPrice = unitPrice;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPhoto() {
            if(this.id == 136101) {
                return R.drawable.ic_136101;
            } else if(this.id == 136102) {
                return R.drawable.ic_136102;
            } else if(this.id == 136103) {
                return R.drawable.ic_136103;
            } else if(this.id == 136104) {
                return R.drawable.ic_136104;
            } else if(this.id == 136105) {
                return R.drawable.ic_136105;
            } else if(this.id == 136106) {
                return R.drawable.ic_136106;
            } else if(this.id == 136107) {
                return R.drawable.ic_136107;
            } else if(this.id == 136108) {
                return R.drawable.ic_136108;
            } else if(this.id == 136109) {
                return R.drawable.ic_136109;
            } else if (this.id == 136110) {
                return R.drawable.ic_136110;
            } else {
                return R.mipmap.ic_launcher;
            }
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", photo=" + photo +
                ", unitPrice=" + unitPrice +
                ", note='" + note + '\'' +
                '}';
    }
}
