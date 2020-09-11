package com.example.projectfinal.models.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MealsModel implements Serializable
{



    @SerializedName("id")
    private int id;



    @SerializedName("name")
    private String UserName;

    @SerializedName("price")
    private String Price;

    @SerializedName("descr")
    private String Description;

    @SerializedName("image")
    private String userImage;

    @SerializedName("category")
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
