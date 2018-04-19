package com.example.mohit.scoocart;

/**
 * Created by mohit on 3/8/2018.
 */

public class category {

    String imageid;
    String product_id;
    String category;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public category(String imageid, String category,String product_id) {
        this.imageid = imageid;
        this.category = category;
        this.product_id=product_id;
    }
}
