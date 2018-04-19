package com.example.mohit.scoocart;

/**
 * Created by mohit on 3/8/2018.
 */

public class offeroftheday {

    String discount;
    String price;
    String imageid;
    String title;
    String subtitle;
    String product_id;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public offeroftheday(String discount, String price, String imageid, String title, String subtitle, String product_id) {
        this.discount = discount;
        this.price = price;
        this.imageid = imageid;
        this.title = title;
        this.subtitle = subtitle;
        this.product_id=product_id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
