package com.example.pethealthcare_appservice.todoList;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pethealthcare_appservice.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Add_TodoActivity extends AppCompatActivity {

    private static final String TAG = "Add_TodoActivity";

    //주석 좀 자세하게 달기 => 짜증남
    //인텐트로 값넘기는 거는 일단 성공인감?
    //앞에서 intent로 넘어온 값을 editText의 setText하여 넣어준다
    // intent로 넘어온 값 연결해서 디비의  도큐먼트 이름으로 저장
    // 할일 삭제 누르면 삭제
    // 기능 알람 추가해서 디비에서 값을 가져와 알람 설정 한다.
    // 이러면 기능 마무리


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String todoValue;
    //값 주고 받기 용


    String str_todoName;
    String str_todoDetail;
    String str_check_mon;
    String str_check_tue;
    String str_check_wed;
    String str_check_thr;
    String str_check_fri;
    String str_check_sat;
    String str_check_sun;
    String str_repeatOnOff;
    String str_reNoti;
    String hour;
    String minute;
    String jmen;


    TodoListInfo todoListInfo;

    TimePicker setTimePicker;
    TextView todoName;
    EditText todoDetail;
    CheckBox check_mon;
    CheckBox check_tue;
    CheckBox check_wed;
    CheckBox check_thr;
    CheckBox check_fri;
    CheckBox check_sat;
    CheckBox check_sun;
    Switch repeatOnOff;
    Spinner reNoti;
    String selected_item;

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_setting);


        findViewById(R.id.finish_todoSetting).setOnClickListener(onClickListener);

        Intent jmenIntent = getIntent();
        jmen = jmenIntent.getStringExtra("jmenValues");
        Log.d("안스혐오", jmen);
        todoValue = jmenIntent.getStringExtra("todoValues");

        todoName = (TextView) findViewById(R.id.tTodo);

        todoName.setText(todoValue);

        setTimePicker = (TimePicker) findViewById(R.id.setTimePicker);
        todoDetail = (EditText) findViewById(R.id.todoDetail);
        check_mon = (CheckBox) findViewById(R.id.check_mon);
        check_tue = (CheckBox) findViewById(R.id.check_tue);
        check_wed = (CheckBox) findViewById(R.id.check_wed);
        check_thr = (CheckBox) findViewById(R.id.check_thr);
        check_fri = (CheckBox) findViewById(R.id.check_fri);
        check_sat = (CheckBox) findViewById(R.id.check_sat);
        check_sun = (CheckBox) findViewById(R.id.check_sun);

        repeatOnOff = (Switch) findViewById(R.id.repeatOnOff);
        reNoti = (Spinner) findViewById(R.id.reNoti);
    }

    //입력 값 얻어오기
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.finish_todoSetting:
                    addTodo();
                    break;
            }
        }
    };


    private void addTodo() {

//입력값 디비에 넣어주기
        setTimePicker = (TimePicker) findViewById(R.id.setTimePicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            hour = setTimePicker.getHour() + "";
            minute = setTimePicker.getMinute() + "";
        } else {
            hour = setTimePicker.getCurrentHour() + "";
            minute = setTimePicker.getCurrentMinute() + "";
        } //TimePicker 값 받기

        if (check_mon.isChecked()) {
            str_check_mon = "월요일";
            check_mon.setChecked(false);
        }
        if (check_tue.isChecked()) {
            str_check_tue = "화요일";
            check_tue.setChecked(false);
        }
        if (check_wed.isChecked()) {
            str_check_wed = "수요일";
            check_wed.setChecked(false);
        }
        if (check_thr.isChecked()) {
            str_check_thr = "목요일";
            check_thr.setChecked(false);
        }
        if (check_fri.isChecked()) {
            str_check_fri = "금요일";
            check_fri.setChecked(false);
        }
        if (check_sat.isChecked()) {
            str_check_sat = "토요일";
            check_sat.setChecked(false);
        }
        if (check_sun.isChecked()) {
            str_check_sun = "일요일";
            check_sun.setChecked(false);
        }
        //checkbox 값

        if (repeatOnOff.isChecked()) {
            str_repeatOnOff = "On";
        } else {
            str_repeatOnOff = "Off";
        }
        // Switch 값

        str_todoName = ((TextView) findViewById(R.id.tTodo)).getText().toString();
        str_todoDetail = ((EditText) findViewById(R.id.todoDetail)).getText().toString(); //Edit Text 값 가져오기
        str_reNoti = reNoti.getSelectedItem().toString(); //Spinner 값

        if (str_todoName.length() > 0 && str_todoDetail.length() > 0) {
            todoListInfo = new TodoListInfo(str_todoName, str_todoDetail, str_check_mon, str_check_tue
                    , str_check_wed, str_check_thr, str_check_fri, str_check_sat
                    , str_check_sun, str_repeatOnOff, str_reNoti, hour, minute);

            db.collection("users").document(user.getUid()).collection("pets").document(jmen).collection("todoLists").document(str_todoName).set(todoListInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            startToast("할 일 등록 성공");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            startToast("할 일 등록 실패");
                        }
                    });
        } else {
            startToast("할 일 제목과 설명을 적어주세요");
        }
    }


    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startMyActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}


