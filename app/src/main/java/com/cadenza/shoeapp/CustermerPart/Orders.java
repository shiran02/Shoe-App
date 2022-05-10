package com.cadenza.shoeapp.CustermerPart;

public class Orders {
    String  phone_number ;
    String  home_address ;
    String shoe_color ;
    String shoe_size ;
    String user_name ;
    int shoe_qty ;
    int last_total ;


    public Orders(String phone_number, String home_address, String shoe_color, String shoe_size, int shoe_qty, int last_total,String user_name) {
        this.phone_number = phone_number;
        this.home_address = home_address;
        this.shoe_color = shoe_color;
        this.shoe_size = shoe_size;
        this.shoe_qty = shoe_qty;
        this.last_total = last_total;
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

    public int getShoe_qty() {
        return shoe_qty;
    }

    public int getLast_total() {
        return last_total;
    }

    public String getLast_username() {
        return user_name;
    }
}
