package com.cadenza.shoeapp.profilePart;

public class dataHolder {

    String name,email,contact,password,pimage;

    public dataHolder(String name, String email, String contact, String password, String pimage) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.pimage = pimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }
}
