package com.example.projectfinal.helper;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectfinal.R;
import com.example.projectfinal.database.FavoriteList;
import com.example.projectfinal.utilities.Constants;


import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<FavoriteList>favoriteLists;
    Context context;

    public FavoriteAdapter(List<FavoriteList> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_user123,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        FavoriteList fl=favoriteLists.get(i);
        holder.name.setText(fl.getName());
        if (!TextUtils.isEmpty(favoriteLists.get(i).getImage())) {
            Glide.with(context).load(Constants.BASE_HOST + favoriteLists.get(i).getImage())
                    .apply(new RequestOptions()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.drawable.ic_shopping_cart_black_24dp))
                    .into(holder.image);

        }

    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, price, edS, edM, edC,txtDiscDialog;
        ImageView image, addtocart;
        Button btnDet;
        ImageView fav_btn;

        public ViewHolder(@NonNull View itemView) {
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









}

