package com.unist.npc.queuing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.auth.SessionCallback;
import com.kakao.usermgmt.LoginButton;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;


public class LoginActivity extends Activity {

    private LinearLayout login;
    private LoginButton kakaoLogin;
    private final SessionCallback mySessionCallback = new MySessionStatusCallback();
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Session.initialize(getApplicationContext(), AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN);//MAKE ONLY POSSIBLE FOR KAKAOLOGIN

        kakaoLogin = (LoginButton) findViewById(R.id.com_kakao_login);
        login = (LinearLayout) findViewById(R.id.login);
        session = Session.getCurrentSession();
        session.addCallback(mySessionCallback);

        Logger.d("++ session.isClosed() : " + session.isClosed());
        if (session.isClosed()){
            kakaoLogin.setVisibility(View.VISIBLE);
        } else {
            kakaoLogin.setVisibility(View.GONE);
            if (session.implicitOpen()) {
                kakaoLogin.setVisibility(View.GONE);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_message_for_service_unavailable), Toast.LENGTH_SHORT).show();
                finish();
            }
        }

       /* login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/
    }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            session.removeCallback(mySessionCallback);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
                return;
            }

            super.onActivityResult(requestCode, resultCode, data);
        }

        private class MySessionStatusCallback implements SessionCallback {
            @Override
            public void onSessionOpened() {
                // 프로그레스바 종료

                // 세션 오픈후 보일 페이지로 이동
                final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onSessionClosed(final KakaoException exception) {
                // 프로그레스바 종료
                // 세션 오픈을 못했으니 다시 로그인 버튼 노출.
                kakaoLogin.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSessionOpening() {
                //프로그레스바 시작
            }
        }



}