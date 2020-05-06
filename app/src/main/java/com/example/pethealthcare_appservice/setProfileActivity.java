package com.example.pethealthcare_appservice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class setProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_profile);

// 정보업데이트  이메일 정보 등록

//      if (FirebaseAuth.getInstance().getCurrentUser() == null) {
//            startMyActivity(loginActivity.class);
//        }
//
//        findViewById(R.id.).setOnClickListener(onClickListener);
    }

//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.:
//                    FirebaseAuth.getInstance().signOut();
//                    startMyActivity(loginActivity.class);
//                    finish();
//                    break;
//            }
//        }
//    };
//
//    private void startMyActivity(Class activity) {
//        Intent intent = new Intent(this, activity);
//        startActivity(intent);
//    }
}
