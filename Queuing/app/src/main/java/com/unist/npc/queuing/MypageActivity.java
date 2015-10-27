package com.unist.npc.queuing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.APIErrorResult;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.usermgmt.LogoutResponseCallback;
import com.kakao.usermgmt.UserManagement;
import com.kakao.util.KakaoParameterException;

/**
 * Created by mark_mac on 2015. 8. 9..
 */
public class MypageActivity extends Activity{

    private TextView logout_btn;
    Switch switch_notification;
    public static SharedPreferences pref;
    SharedPreferences.Editor editor;
    private TextView kakao_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        logout_btn = (TextView) findViewById(R.id.logout_btn);
        kakao_btn = (TextView) findViewById(R.id.kakao_btn);
        pref = getApplicationContext().getSharedPreferences("Notification_ONOFF", MODE_PRIVATE);
        editor = pref.edit();

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onSuccess(final long userId) {
                        redirectLoginActivity();
                    }

                    @Override
                    public void onFailure(final APIErrorResult apiErrorResult) {
                        //redirectLoginActivity();
                        Log.e("LOGOUTFAIL", "ERROR : " + apiErrorResult);
                    }
                });
            }

        });
        kakao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final KakaoLink kakaoLink = KakaoLink.getKakaoLink(MypageActivity.this);
                    final KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
                    kakaoTalkLinkMessageBuilder.addText("Test");
                    final String linkContents = kakaoTalkLinkMessageBuilder.build();
                    kakaoLink.sendMessage(linkContents, MypageActivity.this);
                } catch (KakaoParameterException e) {
                    e.printStackTrace();
                }


            }
        });

        switch_notification = (Switch) findViewById(R.id.switch_notification);

        switch_notification.setChecked(pref.getBoolean("Notification",true));
        switch_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("Notification", true);
                    editor.commit();
                    Log.d("NOTIFY", "TF : " + pref.getBoolean("Notification", true));
                } else {
                    editor.putBoolean("Notification", false);
                    editor.commit();
                    Log.d("NOTIFY", "TF : " + pref.getBoolean("Notification", true));

                }
            }
        });

    }
    protected void redirectMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    protected void redirectLoginActivity() {

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("com.unist.npc.queuing.", Context.MODE_PRIVATE);
        prefs.edit().remove("IsLogin").apply();
        prefs.edit().putBoolean("IsLogin",false).apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
