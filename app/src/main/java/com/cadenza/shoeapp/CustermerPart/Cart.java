package com.cadenza.shoeapp.CustermerPart;

public class Cart {
    private int One_Price;
    private int shoe_qty;
    private String shoe_name;
    private String shoe_color;

    public Cart(int one_Price, int shoe_qty, String shoe_name, String shoe_color) {
        One_Price = one_Price;
        this.shoe_qty = shoe_qty;
        this.shoe_name = shoe_name;
        this.shoe_color = shoe_color;
    }

    public int getOne_Price() {
        return One_Price;
    }

    public int getShoe_qty() {
        return shoe_qty;
    }

    public String getShoe_name() {
        return shoe_name;
    }

    public String getShoe_color() {
        return shoe_color;
    }
}
