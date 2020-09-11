package com.example.projectfinal.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface usersDAO
{

    @Insert
    public void insert(UserDb... users);
    @Update
    public void update(UserDb... users);
    @Delete
    public void delete(UserDb user);

    @Query("SELECT * FROM Users")
    public List<UserDb> getAllUsers();

    @Query("SELECT * FROM Users WHERE uName = :uName")
    public UserDb searchForUserByID(String uName);

    @Query("SELECT count(*) FROM Users")
    public int getCountOfUsers();

}
