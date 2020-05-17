package com.example.pethealthcare_appservice.pet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pethealthcare_appservice.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


// 반려동물 이름을 리스트로 보여주고 반려동물 이름 클릭시 정보 xml로 넘어가는 부분
// 반려동물 리스트를 추가, 삭제, 수정할 수 있는 액티비티

public class Pet_MainActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<String> petName;
    ArrayAdapter<String> adapter;
    ListView petName_listView;
    EditText pPName;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_main);

        findViewById(R.id.add_pet).setOnClickListener(onClickListener);
        findViewById(R.id.remove_pet).setOnClickListener(onClickListener);
    findViewById(R.id.modify_pet).setOnClickListener(onClickListener);
        petName = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, petName);
        petName_listView = (ListView) findViewById(R.id.petList);
        petName_listView.setAdapter(adapter);
        petName_listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        pPName = (EditText) findViewById(R.id.pPName);


    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_pet:
                    String text = pPName.getText().toString();
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
                    pos = petName_listView.getCheckedItemPosition();
                    if (pos != ListView.INVALID_POSITION) {
                        petName.remove(pos);
                        petName_listView.clearChoices();
                        adapter.notifyDataSetChanged();
//                        remove_petDocument();
                    } else {
                        startToast("선택해주세요!");
                    }
                    break;

                case R.id.modify_pet:
                    pos = petName_listView.getCheckedItemPosition();
                    if (pos != ListView.INVALID_POSITION) {
                        startMyActivity(Add_PetActivity.class);
                    } else {
                        startToast("선택해주세요!");
                    }
                    break;
            }
        }
    };



    private void startMyActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

