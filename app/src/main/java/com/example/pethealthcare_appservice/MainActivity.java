package com.example.pethealthcare_appservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startMyActivity(loginActivity.class);
        }
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);
        findViewById(R.id.uUProfileButton).setOnClickListener(onClickListener);
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

                case R.id.uUProfileButton:
                    startMyActivity(updateProfileActivity.class);
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
