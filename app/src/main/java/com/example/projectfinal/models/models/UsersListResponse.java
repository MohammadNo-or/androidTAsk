package com.example.projectfinal.models.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UsersListResponse  {


    @SerializedName("result")
    private ArrayList<UsersModel> usersList;

    public ArrayList<UsersModel> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<UsersModel> usersList) {
        this.usersList = usersList;
    }
}
