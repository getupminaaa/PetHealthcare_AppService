package com.example.pethealthcare_appservice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SetProfileActivity extends AppCompatActivity {

    private static final String TAG = "SetProfileActivity";

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user_profile);

        findViewById(R.id.setUserProfileButton).setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.setUserProfileButton:
                    if (user == null) {
                        startMyActivity(SignUpActivity.class);
                    } else {
                        setProfile();
                        break;
                    }
            }
        }
    };

    private void setProfile() {
        String name = ((EditText) findViewById(R.id.sUName)).getText().toString();
        String phoneNumber = ((EditText) findViewById(R.id.sUPhoneNum)).getText().toString();
        String address = ((EditText) findViewById(R.id.sUAddress)).getText().toString();

        if (name.length() > 0 && phoneNumber.length() == 11 && address.length() > 0) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            UsersInfo usersInfo = new UsersInfo(name, phoneNumber, address);
            if (user != null) {

                db.collection("users").document(user.getUid()).set(usersInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override

                            public void onSuccess(Void aVoid) {
                                startToast("회원정보등록 성공");
                                startMyActivity(MainActivity.class);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("회원정보등록 실패");
                                Log.w(TAG, "등록실패", e);
                            }
                        });
            }


        } else {
            startToast("내용을 입력해주세요!");
        }

    }

    private void startMyActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
