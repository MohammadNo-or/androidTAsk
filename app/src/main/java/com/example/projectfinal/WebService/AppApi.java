package com.example.projectfinal.WebService;

import com.example.projectfinal.models.models.ResponceCategory;
import com.example.projectfinal.models.models.ResultResponse;
import com.example.projectfinal.models.models.MealsListResponse;
import com.example.projectfinal.models.models.UsersListResponse;
import com.example.projectfinal.utilities.Constants;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AppApi
{

    @FormUrlEncoded
    @POST(Constants.SIGN_UP)
    Call<ResultResponse> signUpWithImage(@Field("name") String uName,
                                         @Field("pass") String uPass,
                                         @Field("phoneUser") String telephone,
                                         @Field("statusDelete") String status,
                                         @Field("image") String image);



    @FormUrlEncoded
    @POST(Constants.Add_CATEGORY_MEALS)
    Call<ResultResponse> addCategoryToMeals(@Field("nameEn") String nameCatEn,
                                         @Field("nameAr") String nameCatAr,
                                         @Field("imageCat") String imageCat);




    @FormUrlEncoded
    @POST(Constants.get_Some_Meal)
    Call<MealsListResponse> getSomeMeals(@Field("filter") String filter, @Field("id") int cat);


    @FormUrlEncoded
    @POST(Constants.get_all_users)
    Call<UsersListResponse> getAllUsers(@Field("filter") String filter);


    @FormUrlEncoded
    @POST(Constants.get_user_navigation)
    Call<UsersListResponse> getUserNavigation(@Field("passNav") String passNavigation);




    @FormUrlEncoded
    @POST(Constants.get_full_Card)
    Call<MealsListResponse> getToCard(@Field("filter") String filter);


    @FormUrlEncoded
    @POST(Constants.LOG_IN)
    Call<ResultResponse> login(
                               @Field("name") String uName,
                               @Field("pass") String uPass);



    @FormUrlEncoded
    @POST(Constants.Update_User)
    Call<ResultResponse> UpdateUser(@Field("name") String uName,
                                    @Field("pass") String uPassOld,
                                    @Field("passNew") String uPassNew,
                                    @Field("image") String imgUp);


    @FormUrlEncoded
    @POST(Constants.Update_Meals)
    Call<ResultResponse> UpdateMeals(@Field("name") String name,
                                     @Field("price") String price,
                                     @Field("descr") String description,
                                     @Field("image") String image
                                     // @Field("category") int cat
    );



    @FormUrlEncoded
    @POST(Constants.get_update_category)
    Call<ResultResponse> getupDateCategory(@Field("catEn") String nameCatEn,
                                           @Field("catAr") String nameCatAr,
                                           @Field("image") String imgCategory,
                                           @Field("catEnNew") String strNewCat);


    @FormUrlEncoded
    @POST(Constants.get_Rec)
    Call<MealsListResponse> getAllUsersWithImage(@Field("filter") String filter);


    @FormUrlEncoded
    @POST(Constants.sign_UP_MEAL)
    Call<ResultResponse> signUpMeal(@Field("name") String name,
                                    @Field("price") String price,
                                    @Field("descr") String description,
                                    @Field("image") String image,
                                    @Field("category") int cat);


    @FormUrlEncoded
    @POST(Constants.Sign_Up_Category)
    Call<ResponceCategory> Sign_Up_Category(@Field("") String s);



    @FormUrlEncoded
    @POST(Constants.delete_to_user)
    Call<ResultResponse> deleteUserAcount(@Field("id") int id,
                                           @Field("statusDelete") String status);





}
