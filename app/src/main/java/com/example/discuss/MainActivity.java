package com.example.discuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button resign;
//    private Button forget;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteStudioService.instance().start(this);

        login = (Button) findViewById(R.id.login);
        resign = (Button) findViewById(R.id.resign);
//        forget = (Button) findViewById(R.id.forget);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


//监听登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(username.getText())) {
                    Toast.makeText(MainActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(MainActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    login(username.getText().toString(), password.getText().toString());//调用登录方法
                }
            }
        });
//跳转到注册页面
        resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,chooseDefinitionActivity.class);
                startActivity(intent);
            }
        });


    }
//登录方法
    public void login(String num, String passw) {
        //数据库查询学号和密码
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(MainActivity.this);
        SQLiteDatabase myDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Cursor cursor = myDatabase.query("stuInformation", new String[]{"Password"}, "Number=?", new String[]{num}, null, null, null, null);

        if (cursor.moveToFirst()) {
            if (cursor.getString(0).equals(passw)) {
                //bundle传参
                Intent intent = new Intent(MainActivity.this, chiefActivity.class);//初始化intent类型对象，传入两个参数，前者为源Activity，以.this为后缀；后者为目标Activity，以.class为后缀
                Bundle bundle = new Bundle();
                bundle.putString("username", username.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);//启动Intent执行跳转操作
            } else {
                Toast.makeText(this, "密码不正确！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "用户名不存在！", Toast.LENGTH_SHORT).show();
            System.out.println("xh"+username.getText().toString());
            System.out.println("mm"+password.getText().toString());
        }
    }

}
