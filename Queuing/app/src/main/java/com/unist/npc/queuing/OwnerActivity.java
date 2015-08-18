package com.unist.npc.queuing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class OwnerActivity extends AppCompatActivity {
    String owner_name="outback_sinchon";

    JSONArray jarray;
    JSONObject jobj;
    ArrayList<CusListItem> items;
    CusListAdapter adapter;
    ListView cus_list;
    ReservDialog reservDialog;
    TextView adduser_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        adduser_btn = (TextView) findViewById(R.id.adduser_btn);
        items = new ArrayList<>();
        adapter = new CusListAdapter(getApplicationContext(),R.layout.activity_owner_item,items);
        cus_list = (ListView) findViewById(R.id.cus_list);
        cus_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new HttpPostRequest_2().execute("out", items.get(position).cus_priority, owner_name,"","","");
                items.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        new HttpPostRequest().execute();
        reservDialog = new ReservDialog(this);
        reservDialog.setTitle("Your Information");
        reservDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

            }
        });

        reservDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (items.size() == 0) {
                    items.add(new CusListItem("1", reservDialog._name, reservDialog._number, "13:00", "Offline"));

                } else {
                    items.add(new CusListItem(String.valueOf(Integer.parseInt(items.get(items.size() - 1).cus_priority) + 1), reservDialog._name, reservDialog._number, "13:00", "Offline"));

                }
                adapter.notifyDataSetChanged();


                new HttpPostRequest_2().execute("in", String.valueOf(Integer.parseInt(items.get(items.size() - 1).cus_priority) + 1), owner_name, reservDialog._number, reservDialog._name, "OFFLINE");

            }
        });
        adduser_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservDialog.show();

            }
        });


    }

    private class HttpPostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... info) {
            String sResult = "Error";

            try {
                URL url = new URL("http://52.69.163.43/queuing/line_parse.php?name="+owner_name);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");



                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                String str;

                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                sResult     = builder.toString();
                Log.e("pass", sResult);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResult;
        }

        @Override
        protected void onPostExecute(String result){
            try {
                jarray = new JSONArray(result);
                for(int i=0;i<jarray.length();i++){
                    jobj = jarray.getJSONObject(i);
                    items.add(new CusListItem(jobj.getString("pid"),jobj.getString("name"),jobj.getString("party"),jobj.getString("time"),jobj.getString("method")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cus_list.setAdapter(adapter);

        }
    }
    private class HttpPostRequest_2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... info) {
            String sResult = "Error";

            try {
                URL url = new URL("http://52.69.163.43/queuing/line_manage.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                String body = "in_out=" + info[0] +"&"
                        +"priority=" + info[1] + "&"
                        +"resname=" + info[2] + "&"
                        +"party=" + info[3] + "&"
                        +"name=" + info[4] + "&"
                        +"method=" + info[5];

                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                osw.write(body);
                osw.flush();

                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                String str;

                while ((str = reader.readLine()) != null) {
                    builder.append(str);
                }
                sResult     = builder.toString();
                Log.e("pass", sResult);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResult;
        }

        @Override
        protected void onPostExecute(String result){

        }
    }
}
