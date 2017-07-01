package com.example.sherlock.heltho.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sherlock.heltho.R;

/**
 * Created by sherlock on 4/6/17.
 */

public class customAdapter  extends BaseAdapter {
    private Context context;
    private final String[] dishName;
    private final String[] restaurantName;
    private final String[] money_info;
    private final String[] deliveryTime;

    public customAdapter(Context context, String[] transfer_details,
                         String [] restaurantName,String[]money, String[] transactionID) {
        this.context = context;
        this.dishName = transfer_details;
        this.restaurantName = restaurantName;
        this.money_info = money;
        this.deliveryTime = transactionID;
    }

    TextView dish_name;
    TextView restaurant_name;
    TextView delivery_time;
    TextView money;

    @Override
    public int getCount() {
        return deliveryTime.length;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.my_food_list_view_item, parent, false);
        dish_name = (TextView)rowView.findViewById(R.id.dish_name);
        restaurant_name = (TextView)rowView.findViewById(R.id.restaurant_name);
        delivery_time  =(TextView)rowView.findViewById(R.id.delivery_time);
        money = (TextView)rowView.findViewById(R.id.money_transacted);
        dish_name.setText(dishName[position]);
        restaurant_name.setText("Order from: "+restaurantName[position]);
        delivery_time.setText("Expected Delivery: "+deliveryTime[position]);
        money.setText(money_info[position]);
        return rowView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}