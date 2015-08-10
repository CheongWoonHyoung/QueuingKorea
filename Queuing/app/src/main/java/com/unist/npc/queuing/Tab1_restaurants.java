package com.unist.npc.queuing;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab1_restaurants extends Fragment {

    Context mContext;
    ListView res_listview;
    ArrayList<ResListItem> items;
    ResListAdapter adapter;
    RelativeLayout layout_img;
    boolean lastItemVisibleFlag;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1_restaurants, container, false);
        mContext = container.getContext();

        res_listview = (ListView) v.findViewById(R.id.res_list);
        layout_img = (RelativeLayout) v.findViewById(R.id.layout_large_img);
        items = new ArrayList<ResListItem>();
        adapter = new ResListAdapter(mContext,R.layout.res_list_item,items);


        new HttpPostRequst().execute("");

        res_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, RestaurantInfo.class);
                startActivity(intent);
            }
        });
        lastItemVisibleFlag = false;
        /*res_listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e("onScroll","YES");
                //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
                lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
                if(lastItemVisibleFlag){
                    Log.e("T&F","TRUE");
                    for(int i=0;i<3;i++) items.add(new ResListItem(0,null,null,null,null,null));
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

        });*/
        return v;

    }
    public class HttpPostRequst extends AsyncTask<String,Void,String>{
        String sResult="error";
        @Override
        protected String doInBackground(String... info) {
            URL url = null;
            try {
                url = new URL("http://52.69.163.43/queuing/get_all_rest_info.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                String post_value = "";

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                osw.write(post_value);
                osw.flush();

                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                sResult = builder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }


            return sResult;
        }
        @Override
        protected void onPostExecute(String result){
            Log.e("RESULT",result);
            String jsonall = result;
            JSONArray jArray = null;
            String name = null;
            String cuisine = null;
            int waiting_people = 0;
            String img_large = null;
            Double x_coordinate = null;
            Double y_coordinate = null;
            String distance = null;
            try{
                jArray = new JSONArray(jsonall);
                JSONObject json_data = null;

                for (int i = 0; i < jArray.length(); i++) {
                    json_data = jArray.getJSONObject(i);
                    name = json_data.getString("name");
                    img_large = json_data.getString("img_large");
                    cuisine = json_data.getString("cuisine");
                    waiting_people = json_data.getInt("waiting_people");
                    x_coordinate = json_data.getDouble("x_coordinate");
                    y_coordinate =json_data.getDouble("y_coordinate");

                        items.add(new ResListItem(img_large, name, cuisine, "?", String.valueOf(waiting_people * 5)));


                }
            }catch(Exception e){
                e.printStackTrace();
            }
            res_listview.setAdapter(adapter);





        }
    }

}
