package com.example.sherlock.heltho.dashboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sherlock.heltho.R;
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
import com.r0adkll.slidr.model.SlidrListener;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_item_particulars);
        chart = (BarChart)findViewById(R.id.graph_ratings);
        details  = (Button)findViewById(R.id.view_calorie_content);
        share = (Button) findViewById(R.id.share_item);
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
                                .listener(new SlidrListener(){
                                    @Override
                                    public void onSlideStateChanged(int state) {

                                    }

                                    @Override
                                    public void onSlideChange(float percent) {

                                    }

                                    @Override
                                    public void onSlideOpened() {

                                    }

                                    @Override
                                    public void onSlideClosed() {

                                    }
                                })
                                .build();

        Slidr.attach(this, config);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_calorie_dialog();
            }
        });
        setRatingChart();
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

