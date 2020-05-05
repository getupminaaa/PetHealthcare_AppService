package com.example.pethealthcare_appservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            startMyActivity(loginActivity.class);
        } else {
            //회원가입 or 로그인
                for (UserInfo profile : user.getProviderData()) {
                    String name = profile.getDisplayName();
                }
            }

        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startMyActivity(loginActivity.class);
                    finish();
                    break;
            }
        }
    };


    private void startMyActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
