package com.example.projectfinal.models.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UsersModel implements Serializable {

    @SerializedName("id")
    private int userId;

    @SerializedName("UserName")
    private String userName;


    @SerializedName("UserPass")
    private String userPassword;

    @SerializedName("phone")
    private String telephone;


    @SerializedName("status")
    private String statusUser;

    @SerializedName("email")
    private String emailUser;

    @SerializedName("imageuser")
    private String imgUser;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }


}