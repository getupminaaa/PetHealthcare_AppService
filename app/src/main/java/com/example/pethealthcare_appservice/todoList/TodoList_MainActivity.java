package com.example.pethealthcare_appservice.todoList;

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

public class TodoList_MainActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<String> todoName;
    ArrayAdapter<String> adapter_todo;
    ListView todoName_listView;
    EditText pTodo;
    int pos;
    String text;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main);

        findViewById(R.id.add_todo).setOnClickListener(onClickListener);
        findViewById(R.id.remove_todo).setOnClickListener(onClickListener);

        todoName = new ArrayList<String>();
        adapter_todo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,todoName);
        todoName_listView = (ListView) findViewById(R.id.todoList);
        todoName_listView.setAdapter(adapter_todo);
        todoName_listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        pTodo =(EditText)findViewById(R.id.pTodo);

    }
//여기서 얻은 에딧텍스트 값을 애드 투둠로 넘겨

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_todo:
                  text = pTodo.getText().toString();
                    if(text.length()>0){
                        todoName.add(text);
                        pTodo.setText("");
                        adapter_todo.notifyDataSetChanged();
                        startMyActivity(Add_TodoActivity.class);
                    }else{
                        startToast("내용을 입력해주세여!");
                    }
                    break;
                case R.id.remove_todo:
                    if(pos!= ListView.INVALID_POSITION){
                        todoName.remove(str);
                        //db삭제 내용
                        todoName_listView.clearChoices();
                        adapter_todo.notifyDataSetChanged();
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


