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
import android.widget.ImageView;
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
    private ImageView table1;
    private ImageView table2;
    private ImageView table3;
    private ImageView table4;
    private ImageView table5;
    private ImageView table6;
    private ImageView table7;
    private ImageView table8;
    private ImageView table9;
    private ImageView table10;
    private ImageView table11;
    private ImageView table12;
    private ImageView table13;
    private ImageView table14;
    private ImageView table15;


    static Boolean state1 = false;
    static Boolean state2 = false;
    static Boolean state3 = false;
    static Boolean state4 = false;
    static Boolean state5 = false;
    static Boolean state6 = false;
    static Boolean state7 = false;
    static Boolean state8 = false;
    static Boolean state9 = false;
    static Boolean state10 = false;
    static Boolean state11 = false;
    static Boolean state12 = false;
    static Boolean state13 = false;
    static Boolean state14 = false;
    static Boolean state15 = false;


    long table1_start;
    long table1_end;
    long table2_start;
    long table2_end;
    long table3_start;
    long table3_end;
    long table4_start;
    long table4_end;
    long table5_start;
    long table5_end;
    long table6_start;
    long table6_end;
    long table7_start;
    long table7_end;
    long table8_start;
    long table8_end;
    long table9_start;
    long table9_end;
    long table10_start;
    long table10_end;
    long table11_start;
    long table11_end;
    long table12_start;
    long table12_end;
    long table13_start;
    long table13_end;
    long table14_start;
    long table14_end;
    long table15_start;
    long table15_end;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.table_grandmother);

   //     setContentView(R.layout.table_management);

        table1 = (ImageView) findViewById(R.id.tb1);
        table2 = (ImageView) findViewById(R.id.tb2);
        table3 = (ImageView) findViewById(R.id.tb3);
        table4 = (ImageView) findViewById(R.id.tb4);
        table5 = (ImageView) findViewById(R.id.tb5);
        table6 = (ImageView) findViewById(R.id.tb6);
        table7 = (ImageView) findViewById(R.id.tb7);
        table8 = (ImageView) findViewById(R.id.tb8);
        table9 = (ImageView) findViewById(R.id.tb9);
        table10 = (ImageView) findViewById(R.id.tb10);
        table11 = (ImageView) findViewById(R.id.tb11);
        table12 = (ImageView) findViewById(R.id.tb12);
        table13 = (ImageView) findViewById(R.id.tb13);
        table14 = (ImageView) findViewById(R.id.tb14);
        table15 = (ImageView) findViewById(R.id.tb15);


        table1.setBackgroundResource(android.R.color.white);
        table2.setBackgroundResource(android.R.color.white);
        table3.setBackgroundResource(android.R.color.white);
        table4.setBackgroundResource(android.R.color.white);
        table5.setBackgroundResource(android.R.color.white);
        table6.setBackgroundResource(android.R.color.white);
        table7.setBackgroundResource(android.R.color.white);
        table8.setBackgroundResource(android.R.color.white);
        table9.setBackgroundResource(android.R.color.white);
        table10.setBackgroundResource(android.R.color.white);
        table11.setBackgroundResource(android.R.color.white);
        table12.setBackgroundResource(android.R.color.white);
        table13.setBackgroundResource(android.R.color.white);
        table14.setBackgroundResource(android.R.color.white);
        table15.setBackgroundResource(android.R.color.white);

        table1.setOnClickListener(new Click(1));
        table2.setOnClickListener(new Click(2));
        table3.setOnClickListener(new Click(3));
        table4.setOnClickListener(new Click(4));
        table5.setOnClickListener(new Click(5));
        table6.setOnClickListener(new Click(6));
        table7.setOnClickListener(new Click(7));
        table8.setOnClickListener(new Click(8));
        table9.setOnClickListener(new Click(9));
        table10.setOnClickListener(new Click(10));
        table11.setOnClickListener(new Click(11));
        table12.setOnClickListener(new Click(12));
        table13.setOnClickListener(new Click(13));
        table14.setOnClickListener(new Click(14));
        table15.setOnClickListener(new Click(15));


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
                case 5:
                    if(!state5) {
                        table5_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state5 = true;
                    }
                    else{
                        table5_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state5 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table5_end-table5_start)+"milli second",Toast.LENGTH_LONG).show();
                    }
                    break;
                case 6:
                    if(!state6) {
                        table6_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state6 = true;
                    }
                    else{
                        table6_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state6 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table6_end-table6_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 7:
                    if(!state7) {
                        table7_start = System.currentTimeMillis();

                        v.setBackgroundResource(android.R.color.black);
                        state7 = true;
                    }
                    else{
                        table7_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state7 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table7_end-table7_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 8:
                    if(!state8) {
                        table8_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state8 = true;
                    }
                    else{
                        table8_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state8 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table8_end-table8_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 9:
                    if(!state9) {
                        table9_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state9 = true;
                    }
                    else{
                        table9_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state9 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table9_end-table9_start)+"milli second",Toast.LENGTH_LONG).show();
                    }
                    break;
                case 10:
                    if(!state10) {
                        table10_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state10 = true;
                    }
                    else{
                        table10_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state10 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table10_end-table10_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 11:
                    if(!state11) {
                        table11_start = System.currentTimeMillis();

                        v.setBackgroundResource(android.R.color.black);
                        state11 = true;
                    }
                    else{
                        table11_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state11 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table11_end-table11_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 12:
                    if(!state12) {
                        table12_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state12 = true;
                    }
                    else{
                        table12_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state12 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table12_end-table12_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 13:
                    if(!state13) {
                        table1_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state13 = true;
                    }
                    else{
                        table13_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state13 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table13_end-table13_start)+"milli second",Toast.LENGTH_LONG).show();
                    }
                    break;
                case 14:
                    if(!state14) {
                        table14_start = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.black);
                        state14 = true;
                    }
                    else{
                        table14_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state14 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table14_end-table14_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
                case 15:
                    if(!state15) {
                        table15_start = System.currentTimeMillis();

                        v.setBackgroundResource(android.R.color.black);
                        state15 = true;
                    }
                    else{
                        table15_end = System.currentTimeMillis();
                        v.setBackgroundResource(android.R.color.white);
                        state15 = false;
                        Toast.makeText(getApplicationContext(),"Time elapsed:" + (table15_end-table15_start)+"milli second",Toast.LENGTH_LONG).show();

                    }

                    break;
            }

        }


    }
}
