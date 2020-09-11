package com.example.projectfinal.learnActiv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import com.example.projectfinal.R;

import java.util.ArrayList;
import java.util.List;

public class A2 extends AppCompatActivity {


    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.gallery, "Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new Model(R.drawable.side_nav_bar, "Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        models.add(new Model(R.drawable.gallery, "Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        models.add(new Model(R.drawable.ic_menu_camera, "Namecard", "Business cards are cards bearing business information about a company or individual."));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100, 50, 100, 50);

        Integer[] colors_temp = {
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent)
        };

        colors = colors_temp;


       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


               if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                   viewPager.setBackgroundColor(

                           (Integer) argbEvaluator.evaluate(
                                   positionOffset,
                                   colors[position],
                                   colors[position + 1]
                           )
                   );
               }

               else {
                   viewPager.setBackgroundColor(colors[colors.length - 1]);
               }



           }

           @Override
           public void onPageSelected(int position) {

           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });


    }
}
