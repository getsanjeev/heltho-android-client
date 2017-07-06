package com.example.sherlock.heltho.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.sherlock.heltho.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrListener;
import com.r0adkll.slidr.model.SlidrPosition;

/**
 * Created by sherlock on 7/7/17.
 */

public class oneDishParticulars extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_item_particulars);
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
    }
}
