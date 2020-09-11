package com.example.projectfinal.models.models;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.WebService.RestClient;
import com.example.projectfinal.helper.RecycleAdapter;
import com.example.projectfinal.helper.RecycleAdapterAllUser;
import com.example.projectfinal.helper.RecycleAdapterSearch;
import com.example.projectfinal.helper.categoeyHorizentalAdapter;
import com.example.projectfinal.helper.categoryVerticalAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityViewModel extends ViewModel {

    MutableLiveData<ArrayList<MealsModel>> myList;


    public MutableLiveData<ArrayList<MealsModel>> getData(String filter) {
        ArrayList<MealsModel> lst = new ArrayList<>();
        MutableLiveData<ArrayList<MealsModel>> myLiveDataList = new MutableLiveData<>();


        myLiveDataList.setValue(lst);
        return myLiveDataList;
    }

//__________________________________________________________________________________________________________________________
//getUllUser

    public MutableLiveData<ArrayList<MealsModel>> getAllUsers(String filter) {


        final MutableLiveData<ArrayList<MealsModel>> myLiveDataList = new MutableLiveData<>();


        Call<MealsListResponse> ball = RestClient.getService().getAllUsersWithImage(filter);
        ball.enqueue(new Callback<MealsListResponse>() {
            @Override
            public void onResponse(Call<MealsListResponse> call, Response<MealsListResponse> response) {

                MealsListResponse d = response.body();

                if (d != null) {
                    ArrayList<MealsModel> x = d.getMealList();
                    if (x.size() > 0) {
                        myLiveDataList.setValue(x);

                        //RecycleAdapter h=new RecycleAdapter(RecycleMealActivity.this,s);

                        //    rec.setAdapter(h);

                    }
                }
            }

            @Override
            public void onFailure(Call<MealsListResponse> call, Throwable t) {

                //Toast.makeText(RecycleMealActivity.this, t+"", Toast.LENGTH_SHORT).show();
            }
        });
        return myLiveDataList;
    }
//__________________________________________________________________________________________________________________________________//

//login:


    public MutableLiveData<String> loginUser(String name, String pass) {

        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().login(name, pass);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse res = response.body();
                if (res != null) {
                    String x = res.getResult() + "";
                    if (x.equals("0")) {

                    } else {
                        myLiveDataList.setValue(x);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                // Toast.makeText(, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return myLiveDataList;


    }


//____________________________________________________________________________________________________________________//


    public MutableLiveData<String> getSignUser(String name, String pass, String telephone, String status, String Image) {

        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        Call<ResultResponse> call = RestClient.getService().signUpWithImage(name, pass, telephone, status, Image);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse res = response.body();
                if (res != null) {
                    String x = res.getResult() + "";
                    if (x.equals("0")) {
                        // Toast.makeText(c, "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(c, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                // Toast.makeText(c, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return myLiveDataList;
    }

    //__________________________________________________________________________________________________________________________
    public MutableLiveData<String> AddMeals(String name, String price,String discr, String Image, int category) {

        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();


        Call<ResultResponse> call = RestClient.getService().signUpMeal(name, price, discr,Image, category);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse res = response.body();
                if (res != null) {
                    String x = res.getResult() + "";
                    if (x.equals("0")) {
                       // Toast.makeText(c, "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(c, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                    }

                }

                }



            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                // Toast.makeText(AddMActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
        return myLiveDataList;

    }

    //_________________________________________________________________________________________________________________________

    public MutableLiveData<String> someMealss(String name, int id, final Context c, final RecyclerView rec) {

        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<MealsListResponse> ball = RestClient.getService().getSomeMeals(name, id);
        ball.enqueue(new Callback<MealsListResponse>() {
            @Override
            public void onResponse(Call<MealsListResponse> call, Response<MealsListResponse> response) {

                MealsListResponse d = response.body();

                if (d != null) {

                    ArrayList<MealsModel> s = d.getMealList();
                    if (s.size() > 0) {

                        RecycleAdapter h = new RecycleAdapter(c, s);

                        rec.setAdapter(h);

                    }
                }
            }

            @Override
            public void onFailure(Call<MealsListResponse> call, Throwable t) {

                Toast.makeText(c, t + "", Toast.LENGTH_SHORT).show();

            }
        });

        return myLiveDataList;
    }

    //_____________________________________________________________________________________________________________________________________
    public MutableLiveData<String> show_Meal_category(String str, final Context c, final RecyclerView RecCat) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();


        Call<ResponceCategory> call = RestClient.getService().Sign_Up_Category("");
        call.enqueue(new Callback<ResponceCategory>() {
            @Override
            public void onResponse(Call<ResponceCategory> call, Response<ResponceCategory> response) {

                ResponceCategory data = response.body();

                if (data != null) {

                    ArrayList<Category> arr = data.getCategoryArrayList();


                    if (arr.size() > 0) {

                        categoryVerticalAdapter a = new categoryVerticalAdapter(c, arr);
                        RecCat.setAdapter(a);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponceCategory> call, Throwable t) {

            }
        });
        return myLiveDataList;
    }

    //___________________________________________________________________________________________________
    public MutableLiveData<String> show_Meal_category_horizental(String str, final Context c, final RecyclerView RecCat) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();


        Call<ResponceCategory> call = RestClient.getService().Sign_Up_Category("");
        call.enqueue(new Callback<ResponceCategory>() {
            @Override
            public void onResponse(Call<ResponceCategory> call, Response<ResponceCategory> response) {

                ResponceCategory data = response.body();

                if (data != null) {

                    ArrayList<Category> arr = data.getCategoryArrayList();


                    if (arr.size() > 0) {

                        categoeyHorizentalAdapter a = new categoeyHorizentalAdapter(c, arr);
                        RecCat.setAdapter(a);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponceCategory> call, Throwable t) {

            }
        });
        return myLiveDataList;
    }

    //_______________________________________________________________________________________________________

//________________________________________________________________________________________________________


    public MutableLiveData<String> addCatInSpinner( final Context c, final Spinner sp) {

        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        Call<ResponceCategory> call = RestClient.getService().Sign_Up_Category("");
        call.enqueue(new Callback<ResponceCategory>() {
            @Override
            public void onResponse(Call<ResponceCategory> call, Response<ResponceCategory> response) {

                final ResponceCategory data = response.body();
               // Toast.makeText(c, "welcome", Toast.LENGTH_SHORT).show();
                if (data != null) {
                    ArrayList<Category> arr = data.getCategoryArrayList();
                    if (arr.size() > 0) {
                         String x[] = new String[arr.size()];
                        for (int i = 0; i < arr.size(); i++) {
                            x[i] = arr.get(i).getCategory();
                        }
                        final ArrayAdapter<String> a = new ArrayAdapter<>(c, android.R.layout.simple_spinner_item, x);
                        sp.setAdapter(a);
                    } else {
                        //  Toast.makeText(c, "thank you", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponceCategory> call, Throwable t) {
                //   Toast.makeText(c, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return myLiveDataList;


    }


//___________________________________________________________________________________________________

    public MutableLiveData<String> update_user(String newName, String oldpass, String newpass, String img, final Context c) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<ResultResponse> call = RestClient.getService().UpdateUser(newName, oldpass, newpass, img);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse res = response.body();
                if (res != null) {
                    String x = res.getResult() + "";
                    if (x.equals("0")) {
                        Toast.makeText(c, "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(c, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(c, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return myLiveDataList;
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    public MutableLiveData<String> add_category_meal(String nameEn, String nameAr, String imgCat, final Context c) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<ResultResponse> call = RestClient.getService().addCategoryToMeals(nameEn, nameAr, imgCat);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse res = response.body();
                if (res != null) {
                    String x = res.getResult() + "";
                    if (x.equals("0")) {
                        Toast.makeText(c, "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(c, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(c, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return myLiveDataList;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    public MutableLiveData<String> all_Meals( final RecyclerView rec, final Context c) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<MealsListResponse> ball = RestClient.getService().getAllUsersWithImage("");
        ball.enqueue(new Callback<MealsListResponse>() {
            @Override
            public void onResponse(Call<MealsListResponse> call, Response<MealsListResponse> response) {

                MealsListResponse d = response.body();

                if (d != null) {
                    // Toast.makeText(c, "welcom", Toast.LENGTH_SHORT).show();
                    ArrayList<MealsModel> s = d.getMealList();
                    if (s.size() > 0) {

                        RecycleAdapter h = new RecycleAdapter(c, s);

                        rec.setAdapter(h);

                    }

                }


            }

            @Override
            public void onFailure(Call<MealsListResponse> call, Throwable t) {

                Toast.makeText(c, t + "", Toast.LENGTH_SHORT).show();

            }
        });


        return myLiveDataList;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public MutableLiveData<String> all_Users(String filter, final RecyclerView rec, final Context c) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<UsersListResponse> balls = RestClient.getService().getAllUsers(filter);

        balls.enqueue(new Callback<UsersListResponse>() {
            @Override
            public void onResponse(Call<UsersListResponse> call, Response<UsersListResponse> response) {


                UsersListResponse data = response.body();
                if (data != null) {
                    ArrayList<UsersModel> s = data.getUsersList();
                    if (s.size() > 0) {
                        RecycleAdapterAllUser re = new RecycleAdapterAllUser(c, s);
                        rec.setAdapter(re);
                    }
                }


            }

            @Override
            public void onFailure(Call<UsersListResponse> call, Throwable t) {

            }
        });


        return myLiveDataList;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////

    public MutableLiveData<String> someMeeeeeeeeeealss(String name, int id, final Context c, final RecyclerView rec) {

        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<MealsListResponse> ball = RestClient.getService().getSomeMeals(name, id);
        ball.enqueue(new Callback<MealsListResponse>() {
            @Override
            public void onResponse(Call<MealsListResponse> call, Response<MealsListResponse> response) {

                MealsListResponse d = response.body();

                if (d != null) {

                    ArrayList<MealsModel> s = d.getMealList();
                    if (s.size() > 0) {

                        RecycleAdapter h = new RecycleAdapter(c, s);

                        rec.setAdapter(h);

                    }
                }
            }

            @Override
            public void onFailure(Call<MealsListResponse> call, Throwable t) {

                Toast.makeText(c, t + "", Toast.LENGTH_SHORT).show();

            }
        });

        return myLiveDataList;
    }
//////////////////////////////////////////////////////////////////////////////////////////////

    public MutableLiveData<String> CardRecycle(String filter, final RecyclerView rec, final Context c) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<MealsListResponse> ball = RestClient.getService().getToCard(filter);
        ball.enqueue(new Callback<MealsListResponse>() {
            @Override
            public void onResponse(Call<MealsListResponse> call, Response<MealsListResponse> response) {

                MealsListResponse d = response.body();

                if (d != null) {
                    Toast.makeText(c, "welcom", Toast.LENGTH_SHORT).show();
                    ArrayList<MealsModel> s = d.getMealList();
                    if (s.size() > 0) {

                        RecycleAdapter h = new RecycleAdapter(c, s);

                        rec.setAdapter(h);

                    }

                }


            }

            @Override
            public void onFailure(Call<MealsListResponse> call, Throwable t) {

                Toast.makeText(c, t + "", Toast.LENGTH_SHORT).show();

            }
        });


        return myLiveDataList;
    }

//*********************************************************************************8



    public MutableLiveData<String> updateCategory(String strEn, String strAr, String imgCat, final Context c, String strNew) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<ResultResponse> ball = RestClient.getService().getupDateCategory(strEn, strAr, imgCat, strNew);

        ball.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {


                ResultResponse res = response.body();
                if (res != null) {
                    String x = res.getResult() + "";
                    if (x.equals("0")) {
                        // Toast.makeText(c, "Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        // Toast.makeText(c, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                    }

                }


            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
              //  Toast.makeText(c, t + "error" + t, Toast.LENGTH_SHORT).show();

            }
        });
        return myLiveDataList;
    }
//*******************************************************************************************************************************

    public MutableLiveData<String> delete_user(int id, String status, final Context c) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<ResultResponse> call = RestClient.getService().deleteUserAcount(id, status);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse res = response.body();
                if (res != null) {
                    String x = res.getResult() + "";
                    if (x.equals("")) {
                      //  Toast.makeText(c, "Failed", Toast.LENGTH_LONG).show();
                    } else {
                       // Toast.makeText(c, "done delete to acount user", Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
              //  Toast.makeText(c, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return myLiveDataList;
    }

    public MutableLiveData<String> all_Meals_search(String filter, final RecyclerView rec, final Context c) {
        final MutableLiveData<String> myLiveDataList = new MutableLiveData<>();

        final Call<MealsListResponse> ball = RestClient.getService().getAllUsersWithImage(filter);
        ball.enqueue(new Callback<MealsListResponse>() {
            @Override
            public void onResponse(Call<MealsListResponse> call, Response<MealsListResponse> response) {

                MealsListResponse d = response.body();

                if (d != null) {
                    // Toast.makeText(c, "welcom", Toast.LENGTH_SHORT).show();
                    ArrayList<MealsModel> s = d.getMealList();
                    if (s.size() > 0) {

                        RecycleAdapterSearch h = new RecycleAdapterSearch(c, s);

                        rec.setAdapter(h);

                    }

                }


            }

            @Override
            public void onFailure(Call<MealsListResponse> call, Throwable t) {

                Toast.makeText(c, t + "", Toast.LENGTH_SHORT).show();

            }
        });


        return myLiveDataList;
    }






}



