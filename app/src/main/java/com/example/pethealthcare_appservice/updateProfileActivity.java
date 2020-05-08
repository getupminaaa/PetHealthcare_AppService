package com.example.pethealthcare_appservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class updateProfileActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();


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
                            String name = documentSnapshot.get("name").toString();
                            String phoneNumber = documentSnapshot.get("phoneNumber").toString();
                            String address = documentSnapshot.get("address").toString();

                            TextView tName = (TextView) findViewById(R.id.uUName);
                            tName.setText(name);
                            TextView tPhoneNumber = (TextView) findViewById(R.id.uUPhoneNum);
                            tPhoneNumber.setText(phoneNumber);

                            TextView tAddress = (TextView) findViewById(R.id.uUAddress);
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

                        break;
                    }


            }
        }
    };



    private void startMyActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
