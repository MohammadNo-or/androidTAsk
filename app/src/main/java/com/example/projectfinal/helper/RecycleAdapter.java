package com.example.projectfinal.helper;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectfinal.R;

import com.example.projectfinal.database.FavoriteList;
import com.example.projectfinal.models.models.MealsModel;

import com.example.projectfinal.ui.ActivityProject.MainActivity;
import com.example.projectfinal.ui.ActivityProject.addToCard;
import com.example.projectfinal.utilities.Constants;

import java.util.ArrayList;

import com.example.projectfinal.ui.ActivityProject.detilesActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.viewitem> implements Filterable {



    ArrayList<MealsModel> items;
    Context context;
    ArrayList<MealsModel> moveListAll;
    public int countRec = 0;

    public RecycleAdapter(Context c, ArrayList<MealsModel> item) {
        items = item;
        context = c;


    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<MealsModel> filterdList = new ArrayList<>();


            FilterResults filterResults = new FilterResults();
            filterResults.values = filterdList;
            return filterResults;
        }


        //run on a ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {


            notifyDataSetChanged();


        }
    };


    //The View Item part responsible for connecting the row.xml with
    // each item in the RecyclerView
    //make declare and initalize
    class viewitem extends RecyclerView.ViewHolder {

        //Declare
        TextView name, price, edS, edM, edC,txtDiscDialog;
        ImageView image, addtocart;
        Button btnDet;
        ImageView fav_btn;

        //initialize
        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            edS = itemView.findViewById(R.id.edSum);
            edM = itemView.findViewById(R.id.edMin);
            edC = itemView.findViewById(R.id.edCount);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.imgcard);
            btnDet = itemView.findViewById(R.id.btnDetails);
            addtocart = itemView.findViewById(R.id.goShop);
            txtDiscDialog = itemView.findViewById(R.id.descrDialog);
            fav_btn=itemView.findViewById(R.id.fav_btns);

        }
    }


    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_user123, parent, false);

        //the itemView now is the row
        //We will add 2 onClicks


        return new viewitem(itemView);
    }

    //________________________________________________________________________________________________
    //to fill each item with data from the array depending on position
    @Override
    public void onBindViewHolder(final viewitem holder, final int position) {

        holder.name.setText(items.get(position).getUserName());
        holder.price.setText(items.get(position).getPrice() + " $ ");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        final MealsModel  productList=items.get(position);
        String pimg=productList.getUserImage();



        if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(productList.getId())!=1)
            holder.fav_btn.setImageResource(R.drawable.ic_favorite_border);
        else
            holder.fav_btn.setImageResource(R.drawable.ic_favorite_red);


        holder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteList favoriteList=new FavoriteList();

                int id=productList.getId();
                String image=productList.getUserImage();
                String name=productList.getUserName();

                favoriteList.setId(id);
                favoriteList.setImage(image);
                favoriteList.setName(name);

                if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(id)!=1){
                    holder.fav_btn.setImageResource(R.drawable.ic_favorite_red);
                    MainActivity.favoriteDatabase.favoriteDao().addData(favoriteList);

                }else {
                    holder.fav_btn.setImageResource(R.drawable.ic_favorite_border);
                    MainActivity.favoriteDatabase.favoriteDao().delete(favoriteList);

                }


            }
        });















      //  *******************************************************************************














        holder.edM.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (countRec > 0) {
                    countRec--;
                }

                holder.edC.setText(Integer.toString(countRec));

            }
        });

        holder.edS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countRec++;
                holder.edC.setText(Integer.toString(countRec));
            }
        });

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, addToCard.class);
                i.putExtra("cardshop",items.get(position).getUserName());
                context.startActivity(i);



/*
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.continer, new AddCategory(),null);
            ft.addToBackStack(null);
                Toast.makeText(context, "ee", Toast.LENGTH_SHORT).show();
            ft.commit();
*/
            }
        });


        if (!TextUtils.isEmpty(items.get(position).getUserImage())) {
            Glide.with(context).load(Constants.BASE_HOST + items.get(position).getUserImage())
                    .apply(new RequestOptions()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.drawable.ic_shopping_cart_black_24dp))
                    .into(holder.image);

        }


        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog bt=new BottomSheetDialog(context,R.style.BottomSheetDialogTheme);
                View view= LayoutInflater.from(context).inflate(R.layout.bottom_sheet_lay,null);
                view.findViewById(R.id.btn_addtocart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"add to cart",Toast.LENGTH_LONG).show();
                        bt.dismiss();
                    }
                });
                ImageView imageView=view.findViewById(R.id.my_image);
                Glide.with(context).load(Constants.BASE_HOST + items.get(position).getUserImage()).into(imageView);
                TextView name=view.findViewById(R.id.txt_prname);
                name.setText(items.get(position).getUserName());

                TextView price=view.findViewById(R.id.txt_prprice);
                price.setText(items.get(position).getPrice());

                TextView txtDiscDialog=view.findViewById(R.id.descrDialog);
                price.setText(items.get(position).getDescription());

                bt.setContentView(view);
                bt.show();
            }
        });









        holder.btnDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                androidx.appcompat.app.AlertDialog.Builder a = new AlertDialog.Builder(context);
                a.setNeutralButton("more detailes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(context, detilesActivity.class);

                        i.putExtra("nameDet", items.get(position).getUserName());
                        i.putExtra("priceDet", items.get(position).getPrice());
                        i.putExtra("descrDet", items.get(position).getDescription());
                        i.putExtra("countNum",Integer.toString(countRec));
                        MealsModel u = new MealsModel();
                        u = items.get(position);
                        i.putExtra("completeUser", u);



                        context.startActivity(i);

                    }
                });
                a.setCancelable(false);
                a.setIcon(R.drawable.ic_launcher_background);
                a.setNegativeButton(context.getResources().getString(R.string.No), null);
                a.setPositiveButton(context.getResources().getString(R.string.ok), null);
                a.setTitle(items.get(position).getUserName());
                a.setMessage("the details : " + items.get(position).getDescription() + "\n" + "the price  : " + items.get(position).getPrice() + " $");
                a.show();


                // Intent i=new Intent(context, detailActivity.class);
                //i.putExtra("names",items.get(position).getUserName());
                //MealsModel u=new MealsModel();
                //u=items.get(position);
                //i.putExtra("completeUser",u);
                //context.startActivity(i);
            }
        });


    }

//________________________________________________________________________________________________________________


/*
    public void addFragment(Fragment fragment, boolean addToBackStack, String tag)
    {
        FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();

        if (addToBackStack)
        {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.continer, fragment, tag);
        try{
           ft.commit();
        }catch (Exception e){

        }
    }

*/


    @Override
    public int getItemCount() {
        return items.size();
    }


    // private final View.OnClickListener mOnClickListener = new MyOnClickListener();


//    @Override
//    public void onClick(final View view) {
//        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
//        String item = mList.get(itemPosition);
//        Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
//    }


}
