/**
 * Copyright 2014 Daum Kakao Corp.
 *
 * Redistribution and modification in source or binary forms are not permitted without specific prior written permission. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.unist.npc.queuing;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.kakao.auth.APIErrorResult;
import com.kakao.auth.Session;
import com.kakao.usermgmt.MeResponseCallback;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.UserProfile;
import com.kakao.util.helper.log.Logger;

import java.io.IOException;

/**
 * 유효한 세션이 있다는 검증 후
 * me를 호출하여 가입 여부에 따라 가입 페이지를 그리던지 Main 페이지로 이동 시킨다.
 */


public class TableActivity extends Activity {

    private Context mcontext;
    private Button table1;
    private Button table2;
    private Button table3;
    private Button table4;

    static Boolean state1 = false;
    static Boolean state2 = false;
    static Boolean state3 = false;
    static Boolean state4 = false;

    long table1_start;
    long table1_end;
    long table2_start;
    long table2_end;
    long table3_start;
    long table3_end;
    long table4_start;
    long table4_end;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_management);

        table1 = (Button) findViewById(R.id.tb1);
        table2 = (Button) findViewById(R.id.tb2);
        table3 = (Button) findViewById(R.id.tb3);
        table4 = (Button) findViewById(R.id.tb4);

        table1.setBackgroundResource(android.R.color.white);
        table2.setBackgroundResource(android.R.color.white);
        table3.setBackgroundResource(android.R.color.white);
        table4.setBackgroundResource(android.R.color.white);

        table1.setOnClickListener(new Click(1));
        table2.setOnClickListener(new Click(2));
        table3.setOnClickListener(new Click(3));
        table4.setOnClickListener(new Click(4));

    }


    public class Click implements OnClickListener{

        int mtype;

        Click(int type){

            mtype = type;
        }

        @Override
        public void onClick(View v) {
            switch(mtype){

                case 1:
                    if(!state1) {
                        table1_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state1 = true;
                    }
                    else{
                        table1_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state1 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table1_end-table1_start)+"milli second",Toast.LENGTH_LONG).show();
                    }
                    break;
                case 2:
                    if(!state2) {
                        table2_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state2 = true;
                    }
                    else{
                        table2_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state2 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table2_end-table2_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 3:
                    if(!state3) {
                        table3_start = System.currentTimeMillis();

                        v.setBackgroundResource(android.R.color.black);
                        state3 = true;
                    }
                    else{
                        table3_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state3 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table3_end-table3_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 4:
                    if(!state4) {
                        table4_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state4 = true;
                    }
                    else{
                        table4_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state4 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table4_end-table4_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
            }






        }
    }
}
