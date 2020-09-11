package com.example.projectfinal.models.models;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    private int id;

    @SerializedName("category")
    private String category;

    @SerializedName("imageCategory")
    private String imageCategory;

    public String getImageCategory() {
        return imageCategory;
    }

    public void setImageCategory(String imageCategory) {
        this.imageCategory = imageCategory;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }








}
