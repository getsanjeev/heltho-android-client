package com.example.sherlock.heltho.dashboard;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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


    }

    private void populate_list(int count){
        String [] dish_names = new String[10];
        String [] restaurant_names = new String[10];
        String [] money = new String[10];
        String [] delivery_details = new String[10];

        lv.setAdapter(new customAdapter(this,dish_names,restaurant_names,money,delivery_details));
    }



    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "HELLO "+query, Toast.LENGTH_SHORT).show();

            //use the query to search your data somehow
        }
    }
}
