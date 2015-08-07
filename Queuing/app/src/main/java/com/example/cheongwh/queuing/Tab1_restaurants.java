package com.example.cheongwh.queuing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1_restaurants extends Fragment {

    Context mContext;
    ListView res_listview;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1_restaurants, container, false);
        mContext = container.getContext();

        res_listview = (ListView) v.findViewById(R.id.res_list);
        ArrayList<ResListItem> items = new ArrayList<ResListItem>();
        ResListAdapter adapter = new ResListAdapter(mContext, R.layout.res_list_item, items);
        for (int i = 0; i < 15; i++) items.add(new ResListItem(null, null, null, null, null));
        res_listview.setAdapter(adapter);

        res_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, RestaurantInfo.class);
                startActivity(intent);
            }
        });
        return v;

    }
}