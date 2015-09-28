package com.unist.npc.queuing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mark_mac on 2015. 9. 19..
 */
public class OwnerLogin extends Activity{


    private EditText name;
    private EditText passwd;
    private LinearLayout ownLogin;
    private String s_name, s_passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerlogin);

        name = (EditText) findViewById(R.id.own_name);
        passwd = (EditText) findViewById(R.id.own_password);
        ownLogin = (LinearLayout) findViewById(R.id.own_login);

        ownLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_name = name.getText().toString();
                s_passwd = passwd.getText().toString();
                new HttpPostRequest().execute(s_name,s_passwd);

            }
        });
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        passwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private class HttpPostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... info) {
            String sResult = "Error";

            try {
                URL url = new URL("http://52.69.163.43/queuing/owner_login.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                String body = "name=" + info[0] +"&"
                        +"passwd=" + info[1];

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
                Log.e("CHECK", sResult);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResult;
        }

        @Override
        protected void onPostExecute(String result){
            if(result.equals("SUCCESS")) {
                Toast.makeText(getApplicationContext(), "LOGIN COMPLETE!", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences("OWNER_PREFS", MODE_PRIVATE).edit();
                editor.putString("name", s_name);
                editor.putString("passwd", s_passwd);
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), Owner_mainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "LOGIN FAILED!", Toast.LENGTH_SHORT).show();
            }
            /*DBManager_reserv manager = new DBManager_reserv(getApplicationContext(), "reserv_info.db", null, 1);
            manager.insert("insert into RESERV_INFO values (" + Integer.getInteger(result) + ",'" + resname + "','3','" +dummy_name+ "')");
            Log.e("CONFIRM",":"+manager.returnPid()+" "+manager.returnName()+" "+manager.returnParty());*/

        }
    }
}
