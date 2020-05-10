package com.example.pethealthcare_appservice;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegiPetActivity extends AppCompatActivity {

    private static final String TAG = "RegiPetActivity";
    Spinner spinner_species;
    String tSpecies;
    Spinner spinner_breed;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi_pet);
        findViewById(R.id.pRegiButton).setOnClickListener(onClickListener);

        spinner_species = (Spinner) findViewById(R.id.pSpecies);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.pSpecies, android.R.layout.simple_spinner_item);
        spinner_species.setAdapter(adapter);
        spinner_species.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//        TextView tSpecies = (TextView) spinner_species.getSelectedItem();
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startToast(tSpecies+"호출");
                    case 1:
                        startToast(tSpecies+"호출");
                    case 2:
                        startToast(tSpecies+"호출");
                    case 3:
                        startToast(tSpecies+"호출");
                    case 4:
                        startToast(tSpecies+"호출");
                    case 5:
                        startToast(tSpecies+"호출");
                    case 6:
                        startToast(tSpecies+"호출");
                    case 7:
                        startToast(tSpecies+"호출");
                    case 8:
                        startToast(tSpecies+"호출");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
//

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pRegiButton:


            }
        }
    };

    //
//    private void setProfile() {
//        String name = ((EditText) findViewById(R.id.sUName)).getText().toString();
//        String phoneNumber = ((EditText) findViewById(R.id.sUPhoneNum)).getText().toString();
//        String address = ((EditText) findViewById(R.id.sUAddress)).getText().toString();
//
//        if (name.length() > 0 && phoneNumber.length() == 11 && address.length() > 0) {
//
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//            UsersInfo usersInfo = new UsersInfo(name, phoneNumber, address);
//
//            if (user != null) {
//
//                db.collection("users").document(user.getUid()).set(usersInfo)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//
//                            public void onSuccess(Void aVoid) {
//                                startToast("회원정보등록 성공");
//                                startMyActivity(MainActivity.class);
//                                finish();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                startToast("회원정보등록 실패");
//                                Log.w(TAG, "등록실패", e);
//                            }
//                        });
//            }
//
//
//        } else {
//            startToast("내용을 입력해주세요!");
//        }
//
//    }
//
//    private void startMyActivity(Class activity) {
//        Intent intent = new Intent(this, activity);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//    }
//
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
