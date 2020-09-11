package com.example.projectfinal.helper;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectfinal.R;
import com.example.projectfinal.models.models.MealsModel;
import com.example.projectfinal.ui.ActivityProject.addToCard;
import com.example.projectfinal.utilities.Constants;

import java.util.ArrayList;


public class CardRecycleAdapter extends RecyclerView.Adapter<CardRecycleAdapter.viewitem> implements Filterable {


    //int countRec=0;
    ArrayList<MealsModel> items;
    Context context;
    ArrayList<MealsModel> moveListAll;
    public int countRec = 0;

    public CardRecycleAdapter(Context c, ArrayList<MealsModel> item) {
        items = item;
        context = c;


    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<MealsModel> filterdList=new ArrayList<>();



FilterResults filterResults=new FilterResults();
            filterResults.values=filterdList;
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
        private TextView name, price, edS, edM, edC;
        ImageView image, Shopping;
        Button btnDet;

        //initialize
        public viewitem(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameCard);
            edS = itemView.findViewById(R.id.edSumCard);
            edM = itemView.findViewById(R.id.edMinCard);
            edC = itemView.findViewById(R.id.edCountCard);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.imgCardd);
            btnDet = itemView.findViewById(R.id.btnDetailsCard);
            Shopping = itemView.findViewById(R.id.goCard);

        }
    }


    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customcardview, parent, false);

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
                Intent i=new Intent(context, addToCard.class);

                context.startActivity(i);
            }
        });

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        holder.Shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, addToCard.class);
              //  i.putExtra("cardshop",items.get(position).getUserName());
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
                            .placeholder(R.drawable.ic_launcher_background))
                    .into(holder.image);

        }


        holder.btnDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder a = new AlertDialog.Builder(context);
                a.setNeutralButton("Buy", null);
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
