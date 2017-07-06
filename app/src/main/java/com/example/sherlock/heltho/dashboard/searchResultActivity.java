package com.example.sherlock.heltho.dashboard;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.sherlock.heltho.R;
import com.example.sherlock.heltho.utilities.customAdapter;
import com.squareup.picasso.Picasso;

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(searchResultActivity.this,oneDishParticulars.class));
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
        String mphoto[] = {"https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fdl.myket.ir%2Fimageresizing%2Ficon%2Fsmall%2Fcom.kidsfoodinc.android_make_chinesefoodkf.png&imgrefurl=https%3A%2F%2Fmyket.ir%2Fapp%2Fcom.kidsfoodinc.android_make_chinesefoodkf%2F%3Flang%3Den&docid=h2N6Qu7s5Y73QM&tbnid=RAaZnNuIkFeXRM%3A&vet=10ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwjdASgCMAI..i&w=128&h=128&itg=1&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwjdASgCMAI&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fimage.flaticon.com%2Ficons%2Fpng%2F128%2F133%2F133387.png&imgrefurl=http%3A%2F%2Fwww.flaticon.com%2Ffree-icons%2Fchinese-food_96824&docid=GNv70jRAibVf_M&tbnid=wcSrDKlhIjPgmM%3A&vet=10ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwjiASgHMAc..i&w=128&h=128&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwjiASgHMAc&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fdiarykeetus.files.wordpress.com%2F2016%2F11%2Fveg-momos.jpg%3Fw%3D256%26h%3D256%26crop%3D1&imgrefurl=https%3A%2F%2Fwww.tenter.in%2Findex.php%3Fpage%3D23%26type%25255B%25255D%3Dd%25255B%25255D%3D80%26type%25255B%25255D%3Dc%25255B%25255D%3D359&docid=0TiM7czPsAUgHM&tbnid=3X2IRXxhXKt08M%3A&vet=10ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwj-ASgaMBo..i&w=256&h=256&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwj-ASgaMBo&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=http%3A%2F%2Fwww.angelfire.com%2Ftrek%2Fhillmans%2Fchdimsm3.gif&imgrefurl=http%3A%2F%2Fwww.angelfire.com%2Ftrek%2Fhillmans%2Flinks.html&docid=2HSBJEaA8K3ufM&tbnid=qHgfXcqlVHgo-M%3A&vet=10ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwiOAigqMCo..i&w=128&h=128&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwiOAigqMCo&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fhayleyscooking.files.wordpress.com%2F2015%2F07%2Fchinese-cabbage-asian.jpg%3Fw%3D256%26h%3D256%26crop%3D1&imgrefurl=https%3A%2F%2Fhayleyscooking.com%2Fcategory%2Fdairy-free%2Fsalads-dairy-free%2F&docid=GPVTuqbXL6LDOM&tbnid=2FzQff8vsIRGiM%3A&vet=10ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwiiAig9MD0..i&w=256&h=256&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwiiAig9MD0&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=http%3A%2F%2Fwww.anyfooddelivery.com%2Fimages%2Finspired_entrees.jpg&imgrefurl=http%3A%2F%2Fwww.anyfooddelivery.com%2F&docid=t7pZK5PPAaJ9_M&tbnid=vNXachpjFvxrOM%3A&vet=10ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwjHAihiMGI..i&w=128&h=128&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjwifSLr-_UAhXKLo8KHen8Dx4QMwjHAihiMGI&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fstatic.wixstatic.com%2Fmedia%2F89dcee_eadfe8402d424195b0729298ab7c1796.jpg_256&imgrefurl=https%3A%2F%2Fwww.bigelowstreetbites.com%2Fhome%2Fpage%2F1&docid=P2DDLL_rr0Xt4M&tbnid=bp0MNpia6IETsM%3A&vet=10ahUKEwjvxbO9r-_UAhUHpY8KHdfLDBo4ZBAzCAkoBzAH..i&w=256&h=256&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjvxbO9r-_UAhUHpY8KHdfLDBo4ZBAzCAkoBzAH&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fstatic.wixstatic.com%2Fmedia%2Fb18ed6_fa14fd167dc14b089f43028643232885~mv2_d_2448_2448_s_4_2.jpg_256&imgrefurl=https%3A%2F%2Fwww.odoyleruleseh.com%2Fsingle-post%2F2016%2F04%2F24%2FEating--The-Hobby-That-Always-Keeps-Me-Curious&docid=fVfrqBgwHqORbM&tbnid=7SeH6jwStnvhpM%3A&vet=10ahUKEwjvxbO9r-_UAhUHpY8KHdfLDBo4ZBAzCBkoFzAX..i&w=256&h=256&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjvxbO9r-_UAhUHpY8KHdfLDBo4ZBAzCBkoFzAX&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=http%3A%2F%2Fimages.bigoven.com%2Fimage%2Fupload%2Ft_recipe-256%2Fchinese-beef-tripe-with-ginger-389ec0.jpg&imgrefurl=https%3A%2F%2Fwww.bigoven.com%2Frecipe%2Fchinese-beef-tripe-with-ginger-and-garlic%2F1779954&docid=pV1MX8eZZIdLHM&tbnid=W_TD9_gtXXI8XM%3A&vet=10ahUKEwjvxbO9r-_UAhUHpY8KHdfLDBo4ZBAzCBMoETAR..i&w=256&h=256&bih=678&biw=1311&q=chinese%20food%20images&ved=0ahUKEwjvxbO9r-_UAhUHpY8KHdfLDBo4ZBAzCBMoETAR&iact=mrc&uact=8",
        "https://www.google.co.in/imgres?imgurl=https%3A%2F%2Fs-media-cache-ak0.pinimg.com%2F736x%2Fd1%2F74%2Fd1%2Fd174d15515f6bc10686921db442e1475.jpg&imgrefurl=https%3A%2F%2Fuk.pinterest.com%2Fexplore%2Fpizza-prices%2F&docid=HDPTa9ctQX5YCM&tbnid=jLIlj8qV_lWc_M%3A&vet=10ahUKEwjk593_r-_UAhULNI8KHdNbCUoQMwjkASgFMAU..i&w=256&h=256&bih=678&biw=1311&q=pizza%20food%20images&ved=0ahUKEwjk593_r-_UAhULNI8KHdNbCUoQMwjkASgFMAU&iact=mrc&uact=8"};

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
