package com.example.cheongwh.queuing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;


public class RestaurantInfo extends Activity {

    FrameLayout res_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_info);

        res_confirm = (FrameLayout)findViewById(R.id.res_confirm);
        res_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantInfo.this, ConfirmActivity.class);
                startActivity(intent);
            }
        });
    }



}

