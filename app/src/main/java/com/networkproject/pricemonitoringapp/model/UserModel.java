package com.networkproject.pricemonitoringapp.model;

public class UserModel {
    private String name,email,phoneNum,pass,accountType;

    public UserModel() {
        this.name = null;
        this.email = null;
        this.phoneNum = null;
        this.pass = null;
    }

    public UserModel(String name, String email, String phoneNum, String pass, String accountType) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.pass = pass;
        this.accountType = accountType;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
