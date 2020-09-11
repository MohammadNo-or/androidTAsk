package com.example.projectfinal.models.models;

import com.google.gson.annotations.SerializedName;

public class ResultResponse
{
    @SerializedName("result")
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }




}
