package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class resignActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner university;
    private EditText username;
    private TextView nametip;
    private EditText nickname;
    private EditText password;
    private EditText password2;
    private Button resign;
    private int StuOrTea;
    private String school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resign);

        university=(Spinner)findViewById(R.id.university);
        nametip=(TextView)findViewById(R.id.nametip);
        username = (EditText) findViewById(R.id.username);
        nickname = (EditText) findViewById(R.id.nickName);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);
        resign = (Button) findViewById(R.id.resign);

        Intent intent=this.getIntent();
        StuOrTea=intent.getIntExtra("StuOrTea",0);
        if(StuOrTea==1){
            System.out.println("StuOrTea="+StuOrTea);
            nametip.setText(" 学   号  ");
        }else if(StuOrTea==-1){
            nametip.setText(" 工   号  ");
        }

        university.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] schools=university.getResources().getStringArray(R.array.universities);
                school=schools[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        resign.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(school.equals("请选择学校")){
            Toast.makeText(this, "学校不能为空，请选择学校！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(username.getText())) {
            Toast.makeText(this, "学号不能为空，请输入学号！", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(nickname.getText())) {
            Toast.makeText(this, "姓名不能为空，请输入姓名！", Toast.LENGTH_SHORT).show();
        } else if (!password.getText().toString().equals(password2.getText().toString())) {
            password.setText(" ");
            password2.setText(" ");
            Toast.makeText(this, "两次密码输入不同，请重新输入！", Toast.LENGTH_LONG).show();
        } else {
            resign(username.getText().toString(), nickname.getText().toString(), password.getText().toString(),school);
            Toast.makeText(this, "注册成功，请重新登录！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(resignActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void resign(String number, String nickname, String password,String school) {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(resignActivity.this);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", nickname);
        values.put("Number", number);
        values.put("Password", password);
        values.put("School", school);
//        System.out.println("插入学生："+StuOrTea);
        if(StuOrTea==1) {
            System.out.println("插入学生："+StuOrTea);
            myDatabase.insert("stuInformation", null, values);
            System.out.println("stu success");
        }else  if(StuOrTea==-1){
            myDatabase.insert("teaInformation", null, values);
        }
        myDatabase.close();
    }
}
