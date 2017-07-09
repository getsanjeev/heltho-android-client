package com.example.sherlock.heltho.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sherlock.heltho.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sherlock on 4/6/17.
 */

public class customAdapterForUserRatings extends BaseAdapter {
    private Context context;
    private final String[] userName;
    private final float[] userRatings;
    private final String[] userReviews;

    public customAdapterForUserRatings(Context context, String[] userName,
                                       String [] userReview, float[]ratings) {
        this.context = context;
        this.userName = userName;
        this.userReviews = userReview;
        this.userRatings = ratings;
    }

    private TextView user_name;
    private TextView user_review;
    private TextView user_ratings;

    @Override
    public int getCount() {
        return userName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.review_item, parent, false);
        user_name = (TextView)rowView.findViewById(R.id.name_commentator);
        user_review = (TextView)rowView.findViewById(R.id.user_review_comment);
        user_ratings  = (TextView)rowView.findViewById(R.id.rating_user_commentator);
        user_name.setText(userName[position]);
        user_review.setText(userReviews[position]);
        user_ratings.setText(Float.toString(userRatings[position])+"★");
        return rowView;
    }


}