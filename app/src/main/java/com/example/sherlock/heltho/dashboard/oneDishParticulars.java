package com.example.sherlock.heltho.dashboard;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.sherlock.heltho.R;
import com.example.sherlock.heltho.utilities.customAdapterForUserRatings;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sherlock on 7/7/17.
 */

public class oneDishParticulars extends AppCompatActivity {

    BarChart chart;
    Button details;
    Button share;
    Button bookmark;
    Button rateReview;
    Button viewOthersReview;
    ListView list;
    private static final String GOOGLE_PLAY_LINK_ITEM = "https://play.google.com/store/apps/details?id=com.plumbum.aapu.household&hl=en";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_item_particulars);
        chart = (BarChart)findViewById(R.id.graph_ratings);
        details  = (Button)findViewById(R.id.view_calorie_content);
        share = (Button) findViewById(R.id.share_item);
        bookmark = (Button)findViewById(R.id.add_wish);
        rateReview =(Button)findViewById(R.id.review_btn);
        viewOthersReview = (Button)findViewById(R.id.review_btn_next);
        Slidr.attach(this);
        SlidrConfig config = new SlidrConfig.Builder()
                .primaryColor(getResources().getColor(R.color.colorPrimary))
                        .secondaryColor(getResources().getColor(R.color.colorPrimaryDark))
                                .position(SlidrPosition.RIGHT)
                                .sensitivity(1f)
                                .scrimColor(Color.BLACK)
                                .scrimStartAlpha(0.8f)
                                .scrimEndAlpha(0f)
                                .velocityThreshold(2400)
                                .distanceThreshold(0.25f)
                                .edge(true)
                                .edgeSize(0.18f) // The % of the screen that counts as the edge, default 18%
                                .build();

        Slidr.attach(this, config);
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmarkItem();
            }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_calorie_dialog();
            }
        });
        viewOthersReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAllReviews();
            }
        });
        rateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
            }
        });
        setRatingChart();
    }

    private void launchAllReviews(){
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.others_review, null);
        list = (ListView)promptsView.findViewById(R.id.list_of_reviews);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptsView);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        String [] users = new String[7];
        String [] reviews = new String[7];
        float [] ratings = new float[7];
        for(int i=0;i<7;i++){
            users[i] = "Jim Moriarty";
            reviews[i] = "Tried this place just because of his catchy name the Kopper kadai First thought which came in our mind that we will gonna have something ery intresting in the kadai of Kopper. Talking about the ambience: Palace is good with amazing ambience and interiors";
            ratings[i] = new Float(4.3);
        }
        for(int i=0;i<7;i++){
            users[i] = "James Watson";
            reviews[i] = " Despite the name, I decided to give this place a shot for a special occasion. Found the cake to be really good and so was the delivery timing.";
            ratings[i] = new Float(4.3);
            i++;
        }
        list.setAdapter(new customAdapterForUserRatings(this,users,reviews,ratings));
        alertDialog.show();
    }

    private void showRatingDialog(){
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.user_ratings_reviews, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptsView);
        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();
        RatingBar Rbar = (RatingBar)promptsView.findViewById(R.id.rating_bar);
        final EditText review = (EditText)promptsView.findViewById(R.id.user_review);
        Button send_review_btn = (Button)promptsView.findViewById(R.id.send_review);
        send_review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reviews = review.getText().toString();
                alertDialog.dismiss();
            }
        });

        Rbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                sendRatings(rating);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void sendRatings(Float rating){

    }

    private void bookmarkItem(){
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                getString(R.string.share_msg) + " " + GOOGLE_PLAY_LINK_ITEM);
        startActivity(Intent.createChooser(shareIntent, "Share with others"));
    }

    public void create_calorie_dialog() {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.calorie_content, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setView(promptsView);
        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        PieChart pie = (PieChart)promptsView.findViewById(R.id.fat_protein);
        pie.setUsePercentValues(true);
        pie.getLegend().setEnabled(false);
        Description ds = new Description();
        ds.setText("");
        pie.setDescription(ds);
        List<PieEntry> entry = new ArrayList<>();
        entry.add(new PieEntry(2f,"Protein"));
        entry.add(new PieEntry(4f,"Carbohydrates"));
        entry.add(new PieEntry(1f,"Fats"));
        entry.add(new PieEntry(3f,"Vitamin"));
        entry.add(new PieEntry(0.5f,"Iron"));
        PieDataSet set = new PieDataSet(entry,"");
        set.setValueFormatter(new PercentFormatter());
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData piData = new PieData(set);
        pie.setData(piData);
        alertDialog.show();
    }


    private void setRatingChart(){
        chart.getLegend().setEnabled(false);
        chart.getXAxis().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setEnabled(false);
        chart.setTouchEnabled(false);
        Description ds = new Description();
        ds.setText("");
        chart.setDescription(ds);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 60f, "5★"));
        entries.add(new BarEntry(1f, 80f, "4★"));
        entries.add(new BarEntry(2f, 50f, "3★"));
        entries.add(new BarEntry(3f, 70f, "2★"));
        entries.add(new BarEntry(4f, 50f, "1★"));
        BarDataSet set = new BarDataSet(entries,"User Ratings");
        set.setColor(getResources().getColor(R.color.colorPrimary));
        BarData data = new BarData(set);
        data.setBarWidth(0.3f);
        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh
        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return entry.getData().toString();
            }
        });

    }

}

