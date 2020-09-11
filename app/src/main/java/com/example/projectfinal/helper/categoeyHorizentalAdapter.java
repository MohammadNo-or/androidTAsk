package com.example.projectfinal.helper;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectfinal.R;
import com.example.projectfinal.models.models.Category;
import com.example.projectfinal.utilities.Constants;

import java.util.ArrayList;

import com.example.projectfinal.ui.ActivityProject.someMeals;

public class categoeyHorizentalAdapter extends RecyclerView.Adapter<categoeyHorizentalAdapter.viewitem> {


    ArrayList<Category> items;
    Context context;

    public categoeyHorizentalAdapter(Context c, ArrayList<Category> item) {
        items = item;
        context = c;

    }

    //The View Item part responsible for connecting the row.xml with
    // each item in the RecyclerView
    //make declare and initalize
    class viewitem extends RecyclerView.ViewHolder {

        //Declare
        TextView name;
        ImageView imgcat;
        Button btn;

        //initialize
        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imgcat = itemView.findViewById(R.id.imgcat);
           // btn = itemView.findViewById(R.id.btnBuy);


        }
    }


    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {


        //the itemView now is the row
        //We will add 2 onClicks


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_category_row, parent, false);


        //this on click is for the row
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "ROW Clicked", Toast.LENGTH_SHORT).show();

            }
        });

        //this on click is for the button
        itemView.findViewById(R.id.imgcat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "Sa7a wa Hana", Toast.LENGTH_SHORT).show();

            }
        });


        return new viewitem(itemView);
    }


    //to fill each item with data from the array depending on position
    @Override
    public void onBindViewHolder(viewitem holder, final int position) {

        holder.name.setText(items.get(position).getCategory());













        if (!TextUtils.isEmpty(items.get(position).getImageCategory())) {
            Glide.with(context).load(Constants.BASE_HOST + items.get(position).getImageCategory())
                    .apply(new RequestOptions()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.drawable.ic_menu_share))
                    .into(holder.imgcat);

        }







        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();

                // Bundle b = new Bundle();
                // b.putInt("idCategory", items.get(position).getId());
                // allMaeals x = new allMaeals();
                //  if(x.getArguments()!=null) {
                //     x.setArguments(b);
                // }
                // ft.replace(R.id.containation,new allMaeals() );
                //ft.addToBackStack(null);
                //ft.commit();


            }
        });

holder.imgcat.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Toast.makeText(context, items.get(position).getCategory(), Toast.LENGTH_SHORT).show();

        Intent x = new Intent(context, someMeals.class);
        x.putExtra("idCategory", items.get(position).getId());
        context.startActivity(x);

    }
});








    }


    @Override
    public int getItemCount() {
        return items.size();
    }

   /* public List<Category> getCheckedItems() {
        ArrayList<Category> checkedItems = new ArrayList<>();
        for (int i = 0; i < getItemCount(); i++) {
            if (checkedItems(i)) {
                checkedItems.add(getItem(i));
                if (s.getSelectedItem().equals(Cat.get(i).getCategory())) {

                    return Cat.get(i).getId();
                }
            }
        }
        return checkedItems;*/

    // }
    // private final View.OnClickListener mOnClickListener = new MyOnClickListener();


//    @Override
//    public void onClick(final View view) {
//        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
//        String item = mList.get(itemPosition);
//        Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
//    }


}
