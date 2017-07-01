package com.example.sherlock.heltho.dashboard;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sherlock.heltho.R;
import com.example.sherlock.heltho.utilities.customAdapter;

/**
 * Created by sherlock on 1/7/17.
 */

public class searchResultActivity extends AppCompatActivity {

    ListView lv;
    Button see_more;
    int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        count = 0;
        lv = (ListView)findViewById(R.id.list_view_history);
        see_more = (Button)findViewById(R.id.more);
        handleIntent(getIntent());
        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMoreSuggestions();
            }
        });
    }

    private void loadMoreSuggestions(){
        count = count + 10;
        populate_list(count);
    }

    private void loadSuggestions(){
        populate_list(count);
    }

    private void populate_list(int count){
        String [] dish_names = new String[10];
        String [] restaurant_names = new String[10];
        String [] money = new String[10];
        String [] delivery_details = new String[10];
        Bitmap [] photo = new Bitmap[10];
        String mdish_names[] = {"Tomato Bruchetta","New McDonalds Columbia Pack","Chilly Potato","Italian Fig Bruchetta","New Paneer Wrap",
        "McDonalds Extra Value Pack","McDonalds 007 Hero Burger","Pork Sausage Pizza","Stanley FarmHouse Pizza","McDonalds McPuff"};
        String [] mrestaurant_names = {"Hudson Caffe","Flamess Restaurant & Cafe Village","Shagun Restaurant","Nandhini Udupi Restaurant",
        "Sagar Ratna","Rico's World Street Food ","QD's Restaurant","QD's Restaurant","Slice Of Italy","Aam Aadmi Pakwaan"};
        int mmoney[] = {125,222,499,199,111,989,79,169,350,120};
        String mdelivery_details[] = {"30min","25min","40min","10min","Just Now","25 min","10min","Just Now","25 min","55min"};
        Bitmap mphoto[] = {BitmapFactory.decodeResource(getResources(), R.drawable.bruchetta),
                BitmapFactory.decodeResource(getResources(), R.drawable.columbia),
                BitmapFactory.decodeResource(getResources(), R.drawable.cp),
                BitmapFactory.decodeResource(getResources(), R.drawable.figbruschetta),
                BitmapFactory.decodeResource(getResources(), R.drawable.mcspicy_paneer_wrap),
                BitmapFactory.decodeResource(getResources(), R.drawable.hero_extra_value),
                BitmapFactory.decodeResource(getResources(), R.drawable.mcdonalds_big_mac_007_hero),
                BitmapFactory.decodeResource(getResources(), R.drawable.ii),
                BitmapFactory.decodeResource(getResources(), R.drawable.stanley_fh),
                BitmapFactory.decodeResource(getResources(), R.drawable.pizza_mcpuff)};

        Log.e("dsd",Integer.toString(mmoney.length) + mdelivery_details.length+mdish_names.length+mrestaurant_names.length+mphoto.length);

        lv.setAdapter(new customAdapter(this,mphoto,mdish_names,mrestaurant_names,mmoney,mdelivery_details));
    }





    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "HELLO "+query, Toast.LENGTH_SHORT).show();
            loadSuggestions();
            //use the query to search your data somehow
        }
    }
}
