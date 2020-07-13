package com.example.pethealthcare_appservice.todoList;

import android.content.Intent;
import android.os.Bundle;
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

public class TodoList_MainActivity extends AppCompatActivity {

    // 할 일 목록을 입력하면 값을 intent로 넘겨서 addTodo의 EditText로 넘겨준다.
    //add를 누르면 리스트목록 아이템을 추가한다. 그 다음 add todo로 넘어간다.

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    ArrayList<String> todoName;
    ArrayAdapter<String> adapter_todo;
    ListView todoName_listView;
    Intent intent;

    EditText pTodo; // 리스트 이름 입력하는 친구 -> 이 친구 값 받아서 인텐트로 넘겨주기!
    int pos; //리스트 위치 카운트하는 친구

    String texts;
    String str;
    String jmen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);

        Intent myIntent = getIntent();
        jmen = myIntent.getStringExtra("intentValues");

        findViewById(R.id.add_todo).setOnClickListener(onClickListener);
        findViewById(R.id.remove_todo).setOnClickListener(onClickListener);

        todoName = new ArrayList<String>();
        adapter_todo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, todoName);
        todoName_listView = (ListView) findViewById(R.id.todoList);
        todoName_listView.setAdapter(adapter_todo);
        todoName_listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        todoName_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                pos = todoName_listView.getCheckedItemPosition();
                str = (String) adapterView.getAdapter().getItem(position);
            } //position 값 받아오기
        });



        pTodo = (EditText) findViewById(R.id.pTodo);
        texts = pTodo.getText().toString(); //pTodo의 값이 text에 들어감


    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_todo:
                    pTodo = (EditText) findViewById(R.id.pTodo);
                    texts = pTodo.getText().toString(); //pTodo의 값이 text에 들어감
                    if (texts.length() > 0) {
                        startMyActivity(Add_TodoActivity.class, jmen);
                        todoName.add(texts);  // 리스트에 text값 넣어줌
                        pTodo.setText("");  // edit Text를 다시 정리해주는 친구
                        adapter_todo.notifyDataSetChanged(); //변경을 알려주는 친구
                    } else {
                    startToast("할일 입력");
                }
                    break;
                case R.id.remove_todo:
                    if (pos != ListView.INVALID_POSITION) {
                        todoName.remove(str);
                        remove_todoListDocument();
                        todoName_listView.clearChoices();
                        adapter_todo.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    // 여기서ㅏ 에딧텍스트 값ㄱ을 ㅁ옆의 텍뷰로 셋텏스트 할꺼임 DONE

    private void remove_todoListDocument() {
        db.collection("users").document(user.getUid()).collection("pets").document(jmen).collection("todoLists").document(texts)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("성공");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                startToast("실패");
            }
        });
    }


    private void startMyActivity(Class activity, String str) {

        intent = new Intent(this, activity);
        intent.putExtra("jmenValues", str);
        intent.putExtra("todoValues",texts);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}


