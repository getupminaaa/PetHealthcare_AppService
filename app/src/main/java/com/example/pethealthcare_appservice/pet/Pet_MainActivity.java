package com.example.pethealthcare_appservice.pet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pethealthcare_appservice.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


// 반려동물 이름을 리스트로 보여주고 반려동물 이름 클릭시 정보 xml로 넘어가는 부분
// 반려동물 리스트를 추가, 삭제, 수정할 수 있는 액티비티

public class Pet_MainActivity extends AppCompatActivity {
    private static final String TAG = "Pet_MainActivity";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<String> petName;
    ArrayAdapter<String> adapter;
    ListView petName_listView;
    EditText pPName;
    int pos;
    String text;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_main);

        findViewById(R.id.add_pet).setOnClickListener(onClickListener);
        findViewById(R.id.remove_pet).setOnClickListener(onClickListener);

        petName = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, petName);
        petName_listView = (ListView) findViewById(R.id.petList);
        petName_listView.setAdapter(adapter);
        petName_listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        pPName = (EditText) findViewById(R.id.pPName);

        petName_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                pos = petName_listView.getCheckedItemPosition();
                str = (String) adapterView.getAdapter().getItem(position);
            } //position 값 받아오기
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_pet:
                    text = pPName.getText().toString();
                    if (text.length() > 0) {
                        petName.add(text);
                        pPName.setText("");
                        adapter.notifyDataSetChanged();
                        startMyActivity(Add_PetActivity.class);
                    } else {
                        startToast("내용을 입력해주세요!");
                    }
                    break;
                case R.id.remove_pet:
                    if (pos != ListView.INVALID_POSITION) {
                        petName.remove(str);
                        remove_petDocument();
                        petName_listView.clearChoices();
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    private void remove_petDocument() {
        db.collection("users").document(user.getUid()).collection("pets").document(str)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, str);
                        startToast("성공");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                startToast("실패");
            }
        });
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

