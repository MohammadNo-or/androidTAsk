package com.example.projectfinal.roomdb;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserDb
{

     @NonNull
    @PrimaryKey
private String uName;

private String upassword;



}
