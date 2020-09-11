package com.example.projectfinal.models.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponceCategory {


    @SerializedName("result")
    ArrayList<Category> categoryArrayList;

    public ArrayList<Category> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }
}
