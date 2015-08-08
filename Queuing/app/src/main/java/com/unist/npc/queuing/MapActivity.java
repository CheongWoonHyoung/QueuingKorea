package com.unist.npc.queuing;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapView;


/**
 * Created by cheongwh on 2015. 8. 7..
 */
public class MapActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapView mapView = new MapView(this);
        mapView.setDaumMapApiKey("6f34a566bab64437f455521185842b3f");

        ViewGroup mapViewContainer = (ViewGroup)findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

    }


}
