package com.example.root.rock3test2.model;

/**
 * Created by root on 9/2/18.
 */

public class Product {

    private int sr_no;
    private String product_name;
    private String description;
    private int qty;
    private double price;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public int getSr_no() {
        return sr_no;
    }

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
