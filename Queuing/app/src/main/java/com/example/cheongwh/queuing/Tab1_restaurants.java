package com.example.cheongwh.queuing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1_restaurants extends Fragment {

    Context mContext;
    ListView res_listview;
    ArrayList<ResListItem> items;
    ResListAdapter adapter;

    boolean lastItemVisibleFlag;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1_restaurants, container, false);
        mContext = container.getContext();

        res_listview = (ListView) v.findViewById(R.id.res_list);
        items = new ArrayList<ResListItem>();
        adapter = new ResListAdapter(mContext,R.layout.res_list_item,items);
        items.add(new ResListItem("http://52.69.163.43/img_test/1.jpg",null,null,null,null));
        items.add(new ResListItem("http://52.69.163.43/img_test/2.png", null, null, null, null));
        items.add(new ResListItem("http://52.69.163.43/img_test/3.jpg", null, null, null, null));
        res_listview.setAdapter(adapter);

        res_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, RestaurantInfo.class);
                startActivity(intent);
            }
        });
        lastItemVisibleFlag = false;
        res_listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e("onScroll","YES");
                //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
                lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
                if(lastItemVisibleFlag){
                    Log.e("T&F","TRUE");
                    for(int i=0;i<3;i++) items.add(new ResListItem(null,null,null,null,null));
                    adapter.notifyDataSetChanged();
                }
                else Log.e("T&F","FALSE");
            }
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때 발생되는 스크롤 상태입니다.
                //즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) Log.e("STATE","TRUE");
                else Log.e("STATE","FALSE");
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag) {
                    //TODO 화면이 바닦에 닿을때 처리

                }
            }

        });
        return v;

    }
}
