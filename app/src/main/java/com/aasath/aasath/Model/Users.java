package com.aasath.aasath.Model;

public class Users {

    private String name,phone,Password,image,address;

    public Users(){

    }



    public Users(String name, String phone, String password, String address, String image) {
        this.name = name;
        this.phone = phone;
        this.Password = password;
        this.address = address;
        this.image = image;


    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
