package com.unist.npc.queuing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


public class RestaurantInfo extends AppCompatActivity {
    Toolbar toolbar;
    FrameLayout res_confirm;

    String name = null;
    String cuisine = null;
    String img_large = null;
    String timing = null;
    String location = null;
    Double x_coordinate = null;
    Double y_coordinate = null;
    String phone_num = null;

    ImageView resinfo_image;
    TextView resinfo_name;
    TextView resinfo_cuisine;
    TextView resinfo_timing;
    TextView resinfo_location;
    TextView resinfo_phone_num;
    LinearLayout frame;

    int width_image;
    int height_image;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_info);
        Intent intent = getIntent();
        img_large = intent.getExtras().getString("img_large");
        name = intent.getExtras().getString("name");
        cuisine = intent.getExtras().getString("cuisine");
        timing = intent.getExtras().getString("timing");
        location = intent.getExtras().getString("location");
        phone_num = intent.getExtras().getString("phone_num");
        x_coordinate = intent.getExtras().getDouble("x_coordinate");
        y_coordinate = intent.getExtras().getDouble("y_coordinate");
        this.setResult(Activity.RESULT_OK);


        resinfo_image = (ImageView) findViewById(R.id.resinfo_image);
        resinfo_name = (TextView) findViewById(R.id.resinfo_name);
        resinfo_cuisine = (TextView) findViewById(R.id.resinfo_cuisine);
        resinfo_timing = (TextView) findViewById(R.id.resinfo_timing);
        resinfo_location = (TextView) findViewById(R.id.resinfo_location);
        resinfo_phone_num = (TextView) findViewById(R.id.resinfo_phone_num);
        frame = (LinearLayout) findViewById(R.id.resinfo_frame);


        resinfo_name.setText(name);
        resinfo_cuisine.setText(cuisine);
        resinfo_timing.setText(timing);
        resinfo_location.setText(location);
        resinfo_phone_num.setText(phone_num);

        res_confirm = (FrameLayout)findViewById(R.id.res_confirm);
        res_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantInfo.this, ConfirmActivity.class);
                startActivity(intent);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar_rest_info);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurantinfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        height_image = frame.getHeight();
        width_image = frame.getWidth();
        Picasso.with(getApplicationContext()).load(img_large).resize(width_image, height_image).centerCrop().into(resinfo_image);
    }



}

