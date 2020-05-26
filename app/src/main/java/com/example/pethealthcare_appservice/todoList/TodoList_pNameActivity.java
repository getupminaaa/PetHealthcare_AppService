package com.example.pethealthcare_appservice.todoList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pethealthcare_appservice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TodoList_pNameActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<String> todoPName;
    ArrayAdapter<String> adapter_todoPName;
    ListView todoPName_listView;
    Intent intent;
    String text;

//list itme을 디비에서 가져오자!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_pname);

        todoPName = new ArrayList<String>();
        adapter_todoPName = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoPName);
        todoPName_listView = (ListView) findViewById(R.id.todo_pName_SelectList);
        todoPName_listView.setAdapter(adapter_todoPName);
        getPNameDB();
        todoPName_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected_item = (String)parent.getItemAtPosition(position);
                intent = new Intent(TodoList_pNameActivity.this, TodoList_MainActivity.class);
                intent.putExtra("intentValues",selected_item);
                Log.d("intentValues",selected_item);
//                startMyActivity(.class);
                startActivity(intent);
            }
        });

    }
//받아와야됨 그 추가를


    private void getPNameDB() {
        db.collection("users").document(user.getUid()).collection("pets")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            try {
                                for (DocumentSnapshot document : task.getResult()) {
                                    startToast("성공");
                                    text = document.get("pname").toString();
                                    Log.d("TAG", text);
                                    todoPName.add(text);
                                }
                                adapter_todoPName.notifyDataSetChanged();
                            } catch (Exception e) {
                            }
                        } else {
                            startToast("에러");
                        }
                    }
                });
    }


    private void startMyActivity(Class activity) {
        intent = new Intent(this, activity);

    }

    private void startToast(String msg) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}


