package com.example.sherlock.heltho.dashboard;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sherlock.heltho.R;

/**
 * Created by sherlock on 11/7/17.
 */

public class home extends Fragment {

    CardView cv1;
    CardView cv2;
    CardView cv3;
    CardView cv4;
    CardView cv5;
    CardView cv6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        cv1 = (CardView)getActivity().findViewById(R.id.cv1);
        cv2 = (CardView)getActivity().findViewById(R.id.cv2);
        cv3 = (CardView)getActivity().findViewById(R.id.cv3);
        cv4 = (CardView)getActivity().findViewById(R.id.cv4);
        cv5 = (CardView)getActivity().findViewById(R.id.cv5);
        cv6 = (CardView)getActivity().findViewById(R.id.cv6);

        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),searchResultActivity.class));

            }
        });

        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),searchResultActivity.class));
            }
        });

        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),searchResultActivity.class));
            }
        });

        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),searchResultActivity.class));
            }
        });

        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),searchResultActivity.class));
            }
        });

        return view;
    }
}
