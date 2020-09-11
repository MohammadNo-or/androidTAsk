package com.example.projectfinal.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="favoritelist")
public class FavoriteList {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "prname")
    private String name;


    @ColumnInfo(name = "price")
    private String PriceR;

    @ColumnInfo(name = "description")
    private String DescriptionR;


    @ColumnInfo(name = "category")
    private String categoryR;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getPriceR() {
        return PriceR;
    }

    public void setPriceR(String priceR) {
        PriceR = priceR;
    }

    public String getDescriptionR() {
        return DescriptionR;
    }

    public void setDescriptionR(String descriptionR) {
        DescriptionR = descriptionR;
    }

    public String getCategoryR() {
        return categoryR;
    }

    public void setCategoryR(String categoryR) {
        this.categoryR = categoryR;
    }
}

