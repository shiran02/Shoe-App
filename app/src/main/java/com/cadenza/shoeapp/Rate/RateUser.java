package com.cadenza.shoeapp.Rate;

public class RateUser {

    String user_name;
    String User_comment;
    int User_rate;


    public RateUser() {
    }

    public RateUser(String user_name, String user_comment, int user_rate) {
        this.user_name = user_name;
        User_comment = user_comment;
        User_rate = user_rate;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_comment() {
        return User_comment;
    }

    public void setUser_comment(String user_comment) {
        User_comment = user_comment;
    }

    public int getUser_rate() {
        return User_rate;
    }

    public void setUser_rate(int user_rate) {
        User_rate = user_rate;
    }
}
