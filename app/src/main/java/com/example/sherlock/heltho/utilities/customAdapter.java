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
    private final String[] reason_for_transfer_info;
    private final String[] date_time_info;
    private final String[] money_info;
    private final String[] transaction_id_info;

    public customAdapter(Context context, String[] transfer_details,
                         String [] date_time_info,String[]money, String[] transactionID) {
        this.context = context;
        this.reason_for_transfer_info = transfer_details;
        this.date_time_info = date_time_info;
        this.money_info = money;
        this.transaction_id_info = transactionID;
    }

    TextView reason_for_transfer;
    TextView transaction_id;
    TextView date_time;
    TextView money;

    @Override
    public int getCount() {
        return transaction_id_info.length;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.my_food_list_view_item, parent, false);
        reason_for_transfer = (TextView)rowView.findViewById(R.id.dish_name);
        date_time = (TextView)rowView.findViewById(R.id.restaurant_name);
        transaction_id  =(TextView)rowView.findViewById(R.id.delivery_time);
        money = (TextView)rowView.findViewById(R.id.money_transacted);
        reason_for_transfer.setText(reason_for_transfer_info[position]);
        date_time.setText("Time: "+date_time_info[position]);
        transaction_id.setText("Transaction ID: "+transaction_id_info[position]);
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