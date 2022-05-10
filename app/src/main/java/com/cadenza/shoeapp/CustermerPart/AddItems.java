package com.cadenza.shoeapp.CustermerPart;

public class AddItems {

    String phone_number,home_address,shoe_color,shoe_size,shoe_qty,u_name,shoe_price;

    public AddItems(String phone_number, String home_address, String shoe_color, String shoe_size, String shoe_qty, String u_name, String shoe_price) {
        this.phone_number = phone_number;
        this.home_address = home_address;
        this.shoe_color = shoe_color;
        this.shoe_size = shoe_size;
        this.shoe_qty = shoe_qty;
        this.u_name = u_name;
        this.shoe_price = shoe_price;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getHome_address() {
        return home_address;
    }

    public String getShoe_color() {
        return shoe_color;
    }

    public String getShoe_size() {
        return shoe_size;
    }

    public String getShoe_qty() {
        return shoe_qty;
    }

    public String getU_name() {
        return u_name;
    }

    public String getShoe_price() {
        return shoe_price;
    }
}
