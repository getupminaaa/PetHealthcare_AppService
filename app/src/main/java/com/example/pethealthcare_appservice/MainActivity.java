package com.example.pethealthcare_appservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pethealthcare_appservice.todoList.TodoList_MainActivity;
import com.example.pethealthcare_appservice.pet.Pet_MainActivity;
import com.example.pethealthcare_appservice.sign.loginActivity;
import com.example.pethealthcare_appservice.user.updateProfileActivity;
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
        findViewById(R.id.gUProfileButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoPetListButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoNotification).setOnClickListener(onClickListener);
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

                case R.id.gUProfileButton:
                    startMyActivity(updateProfileActivity.class);

                    break;

                case R.id.gotoPetListButton:
                    startMyActivity(Pet_MainActivity.class);

                    break;
                case R.id.gotoNotification:
                    startMyActivity(TodoList_MainActivity.class);
            }
        }
    };

    private void startMyActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
