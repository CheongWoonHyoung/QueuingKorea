package com.example.cheongwh.queuing;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1 extends Fragment {
    Context mContext;
    ListView res_listview;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);
        mContext = container.getContext();


        res_listview = (ListView) v.findViewById(R.id.res_list);
        ArrayList<ResListItem> items = new ArrayList<ResListItem>();
        ResListAdapter adapter = new ResListAdapter(mContext,R.layout.res_list_item,items);
        items.add(new ResListItem("http://52.69.163.43/img_test/1.jpg",null,null,null,null));
        items.add(new ResListItem("http://52.69.163.43/img_test/2.png",null,null,null,null));
        items.add(new ResListItem("http://52.69.163.43/img_test/3.jpg",null,null,null,null));
        res_listview.setAdapter(adapter);
        return v;
    }
}
