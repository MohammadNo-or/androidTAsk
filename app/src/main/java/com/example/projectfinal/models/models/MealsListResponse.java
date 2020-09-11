package com.example.projectfinal.models.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MealsListResponse
{
    @SerializedName("result")
    private ArrayList<MealsModel> mealList;


    public ArrayList<MealsModel> getMealList() {
        return mealList;
    }

    public void setMealList(ArrayList<MealsModel> mealList) {
        this.mealList = mealList;
    }

    
}
