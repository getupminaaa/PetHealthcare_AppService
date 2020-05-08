package com.example.pethealthcare_appservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class updateProfileActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String name;
    String phoneNumber;
    String address;
    TextView tName;
    TextView tPhoneNumber;
    TextView tAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_profile);

        findViewById(R.id.uUserProfileButton).setOnClickListener(onClickListener);

        if (user != null) {
            db.collection("users").document(user.getUid()).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            tName = (TextView) findViewById(R.id.uUName);
                            tPhoneNumber = (TextView) findViewById(R.id.uUPhoneNum);
                            tAddress = (TextView) findViewById(R.id.uUAddress);

                            name = documentSnapshot.get("name").toString();
                            phoneNumber = documentSnapshot.get("phoneNumber").toString();
                            address = documentSnapshot.get("address").toString();

                            tName.setText(name);
                            tPhoneNumber.setText(phoneNumber);
                            tAddress.setText(address);

//                            Log.d(TAG, "onComplete: " +documentSnapshot.get("name").toString());
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) { //성공시 출력
//                            Log.d(TAG, "onComplete");
                        }
                    });
        }
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.uUserProfileButton:
                    if (user == null) {
                        startMyActivity(SignUpActivity.class);
                    } else {
                        updateProfile();
                        break;
                    }


            }
        }
    };

    private void updateProfile() {
        tName = (TextView) findViewById(R.id.uUName);
        tPhoneNumber = (TextView) findViewById(R.id.uUPhoneNum);
        tAddress = (TextView) findViewById(R.id.uUAddress);


        DocumentReference users = db.collection("users").document(user.getUid());

        users
                .update("name",tName.getText().toString(), "phoneNumber", tPhoneNumber.getText().toString(), "address", tAddress.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("수정 성공");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("수정 실패");
                    }
                });

    }

    private void startMyActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
