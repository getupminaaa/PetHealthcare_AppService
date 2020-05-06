package com.example.pethealthcare_appservice;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class setProfileActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_profile);
    }

//      if (FirebaseAuth
//    }.getInstance().getCurrentUser() == null) {
//            startMyActivity(loginActivity.class);
//        }
//
//        findViewById(R.id.).setOnClickListener(onClickListener);
// 정보업데이트  이메일 정보 등록

    private void setProfile() {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startToast("프로필 등록 완료!");
                        }
                    }
                });
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
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
