package com.example.projectfinal.helper;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectfinal.R;
import com.example.projectfinal.models.models.MainActivityViewModel;
import com.example.projectfinal.models.models.UsersModel;
import com.example.projectfinal.utilities.Constants;
import java.util.ArrayList;


public class RecycleAdapterAllUser extends RecyclerView.Adapter<RecycleAdapterAllUser.viewitem> implements Filterable {

    ArrayList<UsersModel> items;
    Context context;
    ArrayList<UsersModel> moveListAll;
    public int countRec = 0;

    public RecycleAdapterAllUser(Context c, ArrayList<UsersModel> item) {
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

            ArrayList<UsersModel> filterdList = new ArrayList<>();


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
        TextView nameUser, emailUser;
        ImageView imageuser, imgDeleteRec, imgCall;
        ImageView mLove;

        //initialize
        public viewitem(View itemView) {
            super(itemView);
            nameUser = itemView.findViewById(R.id.txtNameUser);
            emailUser = itemView.findViewById(R.id.txtEmailUser);
            imgCall = itemView.findViewById(R.id.imgCall);
            imgDeleteRec = itemView.findViewById(R.id.imgDelete);
            imageuser = itemView.findViewById(R.id.imgUserRec);
            mLove = itemView.findViewById(R.id.love);


        }








    }


    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_users, parent, false);

        //the itemView now is the row
        //We will add 2 onClicks


        return new viewitem(itemView);
    }

    //________________________________________________________________________________________________
    //to fill each item with data from the array depending on position
    @Override
    public void onBindViewHolder(final viewitem holder, final int position) {

        holder.nameUser.setText(items.get(position).getUserName());
        holder.emailUser.setText(items.get(position).getEmailUser());
        //*****************************************************************************************************************************















        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent x = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+items.get(position).getTelephone()));
                context.startActivity(x);


            }
        });

        final Boolean love = false;

        if (love){
            holder.mLove.setImageResource(R.drawable.likeon);
        } else {
            holder.mLove.setImageResource(R.drawable.likeof);
        }





///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



/*
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.continer, new AddCategory(),null);
            ft.addToBackStack(null);
                Toast.makeText(context, "ee", Toast.LENGTH_SHORT).show();
            ft.commit();
*/


        if (!TextUtils.isEmpty(items.get(position).getImgUser())) {
            Glide.with(context).load(Constants.BASE_HOST + items.get(position).getImgUser())
                    .apply(new RequestOptions()
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.drawable.ic_peson))
                    .into(holder.imageuser);

        }


        holder.imgDeleteRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder a = new AlertDialog.Builder(context);
                a.setNeutralButton("Delete Acount me", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       // Intent i = new Intent(context, detilesActivity.class);

                        //i.putExtra("nameDet", items.get(position).getUserName());
                      //  i.putExtra("priceDet", items.get(position).getPrice());
                       // i.putExtra("descrDet", items.get(position).getDescription());
                       // i.putExtra("countNum", Integer.toString(countRec));
                       // MealsModel u = new MealsModel();
                      //  u = items.get(position);
                        //i.putExtra("completeUser", u);


                      //  context.startActivity(i);

                    }
                });
                a.setCancelable(false);
                a.setIcon(R.drawable.ic_launcher_background);
                a.setNegativeButton(context.getResources().getString(R.string.No), null);
                a.setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final  MainActivityViewModel vm = ViewModelProviders.of((AppCompatActivity) context).get(MainActivityViewModel.class);
                        vm.delete_user(items.get(position).getUserId(),"0",context).observe((LifecycleOwner)context, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(context, "assssssel", Toast.LENGTH_SHORT).show();
                            }
                        });





                    }
                });
                a.setTitle(items.get(position).getUserName());
                a.setMessage("Are you sure  Delete Acount");
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

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
    }
}
