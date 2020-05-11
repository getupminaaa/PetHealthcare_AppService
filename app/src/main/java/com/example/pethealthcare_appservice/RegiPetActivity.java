package com.example.pethealthcare_appservice;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegiPetActivity extends AppCompatActivity {

    private static final String TAG = "RegiPetActivity";
    Spinner spinner_species;
    Spinner spinner_breed;
    Spinner spinner_gender;
    Spinner spinner_neutral;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi_pet);
        findViewById(R.id.pRegiButton).setOnClickListener(onClickListener);

        spinner_species = (Spinner) findViewById(R.id.pSpecies);
        spinner_breed = (Spinner) findViewById(R.id.pBreed);
        spinner_gender = (Spinner) findViewById(R.id.pGender);
        spinner_neutral = (Spinner) findViewById(R.id.pNeutral);

        ArrayAdapter adapter_species = ArrayAdapter.createFromResource(context, R.array.pSpecies, android.R.layout.simple_spinner_item);
        spinner_species.setAdapter(adapter_species);
        spinner_species.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        String[] arrayDogSpinner = new String[]{"보더콜리", "웰시코기", "리트리버", "푸들", "비숑"};
                        spinner_breed = (Spinner) findViewById(R.id.pBreed);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item, arrayDogSpinner);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_breed.setAdapter(adapter);
                        break;
                    case 1:
                        String[] arrayCatSpinner = new String[]{"샴", "코리안 숏헤어", "터키쉬 앙고라", "러시안 블루", "먼치킨"};
                        spinner_breed = (Spinner) findViewById(R.id.pBreed);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item, arrayCatSpinner);
                        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_breed.setAdapter(adapter1);
                        break;
                    case 2:
                        String[] arrayLizardSpinner = new String[]{"이구아나", "카멜레온", "레오파드 게코", "비어디드 드래곤", "블루텅 스킨크"};
                        spinner_breed = (Spinner) findViewById(R.id.pBreed);
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item, arrayLizardSpinner);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_breed.setAdapter(adapter2);
                        break;
                    case 3:
                        String[] arrayHamstersSpinner = new String[]{"골든 햄스터", "드워프 햄스터", "윈터화이트 러시안 햄스터", "로보로브스키 햄스터", "컴벨 햄스터"};
                        spinner_breed = (Spinner) findViewById(R.id.pBreed);
                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item, arrayHamstersSpinner);
                        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_breed.setAdapter(adapter3);
                        break;
                    case 4:
                        String[] arrayBirdsSpinner = new String[]{"잉꼬", "왕관 앵무", "카나리아", "히아신스 마코 앵무", "핀치"};
                        spinner_breed = (Spinner) findViewById(R.id.pBreed);
                        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item, arrayBirdsSpinner);
                        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_breed.setAdapter(adapter4);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                startToast("내용을 채워주세요!");
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pRegiButton:
                    RegiPet();
                    break;
            }
        }
    };

    private void RegiPet() {
        String pName = ((EditText) findViewById(R.id.pName)).getText().toString();
        String species = spinner_species.getSelectedItem().toString();
        String breed = spinner_breed.getSelectedItem().toString();
        String gender = spinner_gender.getSelectedItem().toString();
        String neutral = spinner_neutral.getSelectedItem().toString();

        if (pName.length() > 0 && species != null && breed != null && gender != null && neutral != null) {
            FirebaseFirestore pdb = FirebaseFirestore.getInstance();

            PetsInfo petsInfo = new PetsInfo(pName, species, breed, gender, neutral);

            if (user != null) {
                pdb.collection("pets").document(user.getUid()).set(petsInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override

                            public void onSuccess(Void aVoid) {
                                startToast("반려동물등록 성공");

                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("반려동물등록 실패");
                                Log.w(TAG, "등록실패", e);
                            }
                        });
            }
        } else {
            startToast("내용을 채워주세요!");
        }


    }


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
