package com.example.sherlock.heltho.dashboard;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sherlock.heltho.R;

/**
 * Created by sherlock on 1/7/17.
 */

public class searchResultActivity extends AppCompatActivity {

    ListView lv;
    Button see_more;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list);
        handleIntent(getIntent());
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
